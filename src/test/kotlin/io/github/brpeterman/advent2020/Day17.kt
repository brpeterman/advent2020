package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day17 {
	@Test
	fun part1_example() {
		val input = """
			.#.
			..#
			###
		""".trimIndent()
		val cubes = ConwayCubes(input)
		cubes.simulate3d(6)

		assertEquals(112, cubes.activeCells.size)
	}

	@Test
	fun part2_example() {
		val input = """
			.#.
			..#
			###
		""".trimIndent()
		val cubes = ConwayCubes(input)
		cubes.simulate4d(6)

		assertEquals(848, cubes.activeCells.size)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day17.txt").readText()
		val cubes = ConwayCubes(input)
		cubes.simulate3d(6)

		println("Active cells: ${cubes.activeCells.size}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day17.txt").readText()
		val cubes = ConwayCubes(input)
		cubes.simulate4d(6)

		println("Active cells: ${cubes.activeCells.size}")
	}
}
