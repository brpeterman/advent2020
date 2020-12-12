package io.github.brpeterman.advent2020

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertEquals

class Day12 {
	@Test
	fun part1_example() {
		val input = """
			F10
			N3
			F7
			R90
			F11
		""".trimIndent()
		val nav = Navigation()
		val instructions = nav.parseInput(input)
		val finalPosition = nav.navigate(instructions)

		assertEquals(25, abs(finalPosition.first) + abs(finalPosition.second))
	}

	@Test
	fun part2_example() {
		val input = """
			F10
			N3
			F7
			R90
			F11
		""".trimIndent()
		val nav = Navigation()
		val instructions = nav.parseInput(input)
		val finalPosition = nav.navWithWaypoint(instructions)

		assertEquals(286, abs(finalPosition.first) + abs(finalPosition.second))
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day12.txt").readText()
		val nav = Navigation()
		val instructions = nav.parseInput(input)
		val finalPosition = nav.navigate(instructions)

		println("Final distance: ${abs(finalPosition.first) + abs(finalPosition.second)}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day12.txt").readText()
		val nav = Navigation()
		val instructions = nav.parseInput(input)
		val finalPosition = nav.navWithWaypoint(instructions)

		println("Final distance: ${abs(finalPosition.first) + abs(finalPosition.second)}")
	}
}
