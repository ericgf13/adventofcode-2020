package ericgf13.adventofcode.days

import ericgf13.adventofcode.Day

class Day04 : Day(4) {
    private val input = getInputAsString()
    private val passportMap = input.split("\r\n\r\n")
            .map { passport ->
                passport.replace("\r\n", " ")
                        .split(" ")
                        .map { it.substringBefore(":") to it.substringAfter(":") }
                        .toMap()
            }.toList()

    override fun part1(): String {
        return (passportMap.filter { isPassportValid(it, false) }.count()).toString()
    }

    override fun part2(): String {
        return (passportMap.filter { isPassportValid(it, true) }.count()).toString()
    }

    private fun isPassportValid(passport: Map<String, String>, fullCheck: Boolean): Boolean {
        var valid = passport.size == 8 || (passport.size == 7 && !passport.containsKey("cid"))

        if (valid && fullCheck) {
            passport.forEach { (k, v) ->
                valid = when (k) {
                    "byr" -> v.matches(Regex("\\d{4}")) && v.toInt() in 1920..2002
                    "iyr" -> v.matches(Regex("\\d{4}")) && v.toInt() in 2010..2020
                    "eyr" -> v.matches(Regex("\\d{4}")) && v.toInt() in 2020..2030
                    "hgt" -> v.matches(Regex("\\d+(cm|in)")) &&
                            (v.endsWith("cm") && v.substringBefore("cm").toInt() in 150..193 || v.endsWith("in") && v.substringBefore("in").toInt() in 59..76)
                    "hcl" -> v.matches(Regex("#([0-9]|[a-f]){6}"))
                    "ecl" -> setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(v)
                    "pid" -> v.matches(Regex("\\d{9}"))
                    "cid" -> true
                    else -> false
                }
                if (!valid) return false
            }
        }

        return valid
    }
}
