package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day11 : Day(11) {
    private val input = getInputAsList()
    private val directions = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)

    override fun part1(): String {
        return process(1)
    }

    override fun part2(): String {
        return process(2)
    }

    private fun process(part: Int): String {
        var grid = input

        while (true) {
            val newGrid = mutableListOf<String>()
            var change = false

            grid.forEachIndexed { y, line ->
                var newLine = ""

                line.forEachIndexed { x, seat ->
                    newLine += when (seat) {
                        'L' -> {
                            val seats = if (part == 1) getAdjacentSeats(x, y, grid) else getVisibleSeats(x, y, grid)
                            if (seats.none { it == '#' }) {
                                change = true
                                '#'
                            } else {
                                'L'
                            }
                        }
                        '#' -> {
                            val seats = if (part == 1) getAdjacentSeats(x, y, grid) else getVisibleSeats(x, y, grid)
                            val threshold = if (part == 1) 4 else 5
                            if (seats.filter { it == '#' }.count() >= threshold) {
                                change = true
                                'L'
                            } else {
                                '#'
                            }
                        }
                        else -> '.'
                    }
                }

                newGrid.add(newLine)
            }

            grid = newGrid

            if (!change) {
                return grid.map { it.filter { seat -> seat == '#' }.count() }.sum().toString()
            }
        }
    }

    private fun getAdjacentSeats(x: Int, y: Int, grid: List<String>): String {
        return directions.map {
            try {
                grid[y + it.first][x + it.second]
            } catch (e: IndexOutOfBoundsException) {
                ""
            }
        }.joinToString()
    }

    private fun getVisibleSeats(x: Int, y: Int, grid: List<String>): String {
        return directions.map { dir ->
            for (i in 1..100) {
                try {
                    val seat = grid[y + dir.first * i][x + dir.second * i]
                    if (seat != '.') {
                        return@map seat.toString()
                    }
                } catch (e: IndexOutOfBoundsException) {
                    return@map ""
                }
            }
        }.joinToString()
    }
}
