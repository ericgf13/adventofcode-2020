package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day08 : Day(8) {
    private val input = getInputAsList()

    override fun part1(): String {
        return execute(input)
    }

    private fun execute(instructions: List<String>): String {
        var acc = 0
        var index = 0
        val visited = mutableSetOf(0)

        while (true) {
            val instruction = instructions[index]
            val operation = instruction.substring(0, 3)
            val arg = instruction.substring(4).toInt()

            when (operation) {
                "acc" -> {
                    acc += arg
                    index++
                }
                "jmp" -> index += arg
                "nop" -> index++
            }

            if (!visited.add(index)) {
                return acc.toString()
            }

            if (index >= instructions.size) {
                return "SUCCESS$acc"
            }
        }
    }

    override fun part2(): String {
        input.forEachIndexed { index, instruction ->
            val operation = instruction.substring(0, 3)

            if (operation in arrayOf("nop", "jmp")) {
                val tmpInstructions = input.toMutableList()

                if (operation == "nop") {
                    tmpInstructions[index] = instruction.replace("nop", "jmp")
                } else if (operation == "jmp") {
                    tmpInstructions[index] = instruction.replace("jmp", "nop")
                }

                val result = execute(tmpInstructions)
                if (result.startsWith("SUCCESS")) {
                    return result.removePrefix("SUCCESS")
                }
            }
        }
        return ""
    }
}
