package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day9 {
	val cracker = XmasCracker()

	@Test
	fun part1_example() {
		val input = """
			35
			20
			15
			25
			47
			40
			62
			55
			65
			95
			102
			117
			150
			182
			127
			219
			299
			277
			309
			576
		""".trimIndent()
		val values = cracker.parseInput(input)
		val wrongValue = cracker.findInvalidValue(5, values)

		assertEquals(127, wrongValue)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day9.txt").readText()
		val values = cracker.parseInput(input)
		val wrongValue = cracker.findInvalidValue(25, values)

		println("Invalid value: $wrongValue")
	}

	@Test
	fun part2_example() {
		val input = """
			35
			20
			15
			25
			47
			40
			62
			55
			65
			95
			102
			117
			150
			182
			127
			219
			299
			277
			309
			576
		""".trimIndent()
		val values = cracker.parseInput(input)
		val wrongValue = cracker.findInvalidValue(5, values)
		val weakness = cracker.findWeakness(wrongValue, values)

		assertEquals(62, weakness)
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day9.txt").readText()
		val values = cracker.parseInput(input)
		val wrongValue = cracker.findInvalidValue(25, values)
		val weakness = cracker.findWeakness(wrongValue, values)

		println("Weakness: $weakness")
	}
}
