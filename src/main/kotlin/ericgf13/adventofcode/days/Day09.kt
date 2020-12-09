package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day09 : Day(9) {
    private val input = getInputAsList()
    private var part1Result = 0

    override fun part1(): String {
        val preambleSize = 25

        for (i in preambleSize until input.size) {
            val num = input[i].toInt()
            var found = false

            run {
                input.subList(i - preambleSize, i).forEach { val1 ->
                    input.subList(i - preambleSize, i).forEach { val2 ->
                        if (val1 != val2 && val1.toInt() + val2.toInt() == num) {
                            found = true
                            return@run
                        }
                    }
                }
            }

            if (!found) {
                part1Result = num
                return num.toString()
            }
        }
        return ""
    }

    override fun part2(): String {
        for (i in input.indices) {
            val values = mutableSetOf<Int>()

            for (y in i until input.size) {
                values.add(input[y].toInt())
                val sum = values.sum()

                if (sum == part1Result) {
                    return (values.minOrNull()!! + values.maxOrNull()!!).toString()
                }

                if (sum > part1Result) {
                    break
                }
            }
        }
        return ""
    }
}
