package ericgf13.adventofcode

import ericgf13.adventofcode.days.*
import kotlin.system.measureTimeMillis

fun main() {
    val days = mutableListOf<Day>().apply {
        add(Day01())
        add(Day02())
        add(Day03())
        add(Day04())
        add(Day05())
        add(Day06())
        add(Day07())
        add(Day08())
        add(Day09())
        add(Day11())
        add(Day12())
        add(Day15())
    }

    days.forEach {
        val sb = StringBuilder()
        sb.appendLine("===== DAY ${it.dayNum} =====")
        sb.appendLine(" - " + measureTimeMillis { sb.append("Part 1: ${it.part1()}") } + " ms")
        sb.appendLine(" - " + measureTimeMillis { sb.append("Part 2: ${it.part2()}") } + " ms")
        print(sb)
    }
}
