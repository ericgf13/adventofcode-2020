package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day06 : Day(6) {
    private val input = getInputAsString()

    override fun part1(): String {
        return input.split("\r\n\r\n")
                .map { group ->
                    group.replace("\r\n", "").toSet().count()
                }.sum().toString()
    }

    override fun part2(): String {
        return input.split("\r\n\r\n")
                .map { group ->
                    val groupSize = group.split("\r\n").size
                    group.replace("\r\n", "").groupingBy { it }.eachCount().filterValues { it == groupSize }.count()
                }.sum().toString()
    }
}
