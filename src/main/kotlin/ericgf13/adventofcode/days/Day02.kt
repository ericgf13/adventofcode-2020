package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day02 : Day(2) {
    private val pattern = Regex("(\\d+)-(\\d+) (\\w): (\\w*)")
    private val input = getInputAsList().map { pattern.find(it)!!.destructured }

    override fun part1(): String {
        var result = 0

        input.forEach { (min, max, letter, password) ->
            val occurrences = password.filter { it == letter.single() }.length

            if (occurrences in min.toInt()..max.toInt()) {
                result++
            }
        }

        return result.toString()
    }

    override fun part2(): String {
        var result = 0

        input.forEach { (index1, index2, letter, password) ->
            val index1Match = password[index1.toInt() - 1] == letter.single()
            val index2Match = password[index2.toInt() - 1] == letter.single()

            if (index1Match xor index2Match) {
                result++
            }
        }

        return result.toString()
    }
}
