package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day05 : Day(5) {
    private val input = getInputAsList()
    private val seatIds = input.map { pass ->
        var rowMin = 0
        var rowMax = 127

        pass.take(7).forEach { letter ->
            val diff = (rowMax - rowMin) / 2 + 1

            if (letter == 'F') {
                rowMax -= diff
            } else {
                rowMin += diff
            }
        }

        var colMin = 0
        var colMax = 7

        pass.takeLast(3).forEach { letter ->
            val diff = (colMax - colMin) / 2 + 1

            if (letter == 'L') {
                colMax -= diff
            } else {
                colMin += diff
            }
        }

        rowMin * 8 + colMin
    }.sorted().toList()

    override fun part1(): String {
        return seatIds.last().toString()
    }

    override fun part2(): String {
        var previous = 0
        seatIds.forEach {
            if (previous != 0 && it - previous != 1) {
                return (it - 1).toString()
            }
            previous = it
        }
        return ""
    }
}
