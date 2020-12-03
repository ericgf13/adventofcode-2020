package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day01 : Day(1) {
    private val input = getInputAsList().map { it.toInt() }

    override fun part1(): String {
        input.forEach { i ->
            input.forEach {
                if (i + it == 2020)
                    return (i * it).toString()
            }
        }
        return ""
    }

    override fun part2(): String {
        input.forEach { i ->
            input.forEach { y ->
                input.forEach {
                    if (i + y + it == 2020)
                        return (i * y * it).toString()
                }
            }
        }
        return ""
    }
}
