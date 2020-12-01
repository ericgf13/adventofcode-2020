package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day01 : Day(1) {
    private val input = getInputAsList().map { it.toInt() }

    override fun part1(): String {
        var result = 0

        input.forEach { i ->
            input.forEach {
                if (i + it == 2020)
                    result = i * it
            }
        }

        return result.toString()
    }

    override fun part2(): String {
        var result = 0

        input.forEach { i ->
            input.forEach { y ->
                input.forEach {
                    if (i + y + it == 2020)
                        result = i * y * it
                }
            }
        }

        return result.toString()
    }
}
