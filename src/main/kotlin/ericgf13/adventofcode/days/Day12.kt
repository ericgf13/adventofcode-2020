package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day
import ericgf13.adventofcode.util.Coordinate
import kotlin.math.abs

class Day12 : Day(12) {
    private val input = getInputAsList()
    private val directions = mapOf('E' to 0, 'S' to 90, 'W' to 180, 'N' to 270)

    override fun part1(): String {
        var dir = 'E'
        val coord = Coordinate(0, 0)

        input.forEach {
            var action = it[0]
            val value = it.substring(1).toInt()

            if (action == 'F') {
                action = dir
            }

            when (action) {
                'N' -> coord.offsetY(value)
                'S' -> coord.offsetY(-value)
                'E' -> coord.offsetX(value)
                'W' -> coord.offsetX(-value)
                'L' -> dir = getNewDir(dir, value, -1)
                'R' -> dir = getNewDir(dir, value, 1)
            }
        }

        return (abs(coord.x) + abs(coord.y)).toString()
    }

    private fun getNewDir(dir: Char, value: Int, rotation: Int): Char {
        val angle = (directions.getValue(dir) + value * rotation + 360) % 360
        directions.forEach { (k, v) ->
            if (v == angle) {
                return k
            }
        }
        return ' '
    }

    override fun part2(): String {
        val coord = Coordinate(0, 0)
        val waypoint = Coordinate(10, 1)

        input.forEach { instruction ->
            val action = instruction[0]
            val value = instruction.substring(1).toInt()

            when (action) {
                'N' -> waypoint.offsetY(value)
                'S' -> waypoint.offsetY(-value)
                'E' -> waypoint.offsetX(value)
                'W' -> waypoint.offsetX(-value)
                'L' -> rotateWaypoint(waypoint, value, -1)
                'R' -> rotateWaypoint(waypoint, value, 1)
                'F' -> {
                    coord.offsetX(waypoint.x * value)
                    coord.offsetY(waypoint.y * value)
                }
            }
        }

        return (abs(coord.x) + abs(coord.y)).toString()
    }

    private fun rotateWaypoint(waypoint: Coordinate, value: Int, rotation: Int) {
        for (i in 90..value step 90) {
            val tmp = waypoint.x
            waypoint.x = waypoint.y * rotation
            waypoint.y = tmp * rotation * -1
        }
    }
}
