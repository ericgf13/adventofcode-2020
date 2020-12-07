package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day07 : Day(7) {
    private val input = getInputAsList()
    private val containedByBag = HashMap<String, MutableList<Pair<Int, String>>>()
    private val pattern = Regex("(.+) bags contain (.+)\\.")
    private val patternContain = Regex("(\\d+) (.+) bag")

    init {
        input.forEach { rule ->
            val (bag, contain) = pattern.find(rule)!!.destructured
            containedByBag.putIfAbsent(bag, mutableListOf())

            contain.split(", ").forEach {
                if ("no other bags" != it) {
                    val (count, contained) = patternContain.find(it)!!.destructured
                    containedByBag[bag]!!.add(Pair(count.toInt(), contained))
                }
            }
        }
    }

    override fun part1(): String {
        val containers = mutableSetOf<String>()
        getContainers("shiny gold", containers)
        return (containers.size).toString()
    }

    private fun getContainers(bag: String, containers: MutableSet<String>) {
        containedByBag.filterValues { contained ->
            contained.any { it.second == bag }
        }.forEach {
            containers.add(it.key)
            getContainers(it.key, containers)
        }
    }

    override fun part2(): String {
        return getCount("shiny gold").toString()
    }

    private fun getCount(bag: String): Int {
        return containedByBag[bag]!!.fold(0) { count, contained ->
            count + contained.first * (1 + getCount(contained.second))
        }
    }
}
