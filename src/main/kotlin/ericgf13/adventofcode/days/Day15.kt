package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day15 : Day(15) {
    private val input = getInputAsString().split(",").map { it.toInt() }

    override fun part1(): String {
        val spoken = input.toMutableList()

        while (true) {
            val lastNumber = spoken.last()
            val indices = mutableListOf<Int>()
            spoken.forEachIndexed { index, number ->
                if (lastNumber == number) {
                    indices.add(index + 1)
                }
            }

            if (indices.size >= 2) {
                spoken.add(indices.last() - indices.dropLast(1).last())
            } else {
                spoken.add(0)
            }

            if (spoken.size == 2020) {
                return spoken.last().toString()
            }
        }
    }

    override fun part2(): String {
        val indicesByNumber = mutableMapOf<Int, MutableList<Int>>()
        input.forEachIndexed { index, number -> indicesByNumber[number] = mutableListOf(index + 1) }

        var lastNumber = input.last()
        var index = input.size

        while (true) {
            index++

            val indices = indicesByNumber[lastNumber]
            if (indices?.size == 2) {
                val diff = indices[1] - indices[0]
                lastNumber = diff
                addIndex(diff, index, indicesByNumber)
            } else {
                lastNumber = 0
                addIndex(0, index, indicesByNumber)
            }

            if (index == 30_000_000) {
                return lastNumber.toString()
            }
        }
    }

    private fun addIndex(number: Int, index: Int, indicesByNumber: MutableMap<Int, MutableList<Int>>) {
        if (indicesByNumber.containsKey(number)) {
            indicesByNumber[number]!!.add(index)
            if (indicesByNumber[number]!!.size > 2) {
                indicesByNumber[number]!!.removeAt(0)
            }
        } else {
            indicesByNumber[number] = mutableListOf(index)
        }
    }
}
