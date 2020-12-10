package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10 {
	val jolts = Jolts()

	@Test
	fun part1_example1() {
		val input = """
			16
			10
			15
			5
			1
			11
			7
			19
			6
			12
			4
		""".trimIndent()
		val buckets = jolts.bucketJolts(jolts.parseInput(input))

		assertEquals(35, buckets[1]!! * buckets[3]!!)
	}

	@Test
	fun part1_example2() {
		val input = """
			28
			33
			18
			42
			31
			14
			46
			20
			48
			47
			24
			23
			49
			45
			19
			38
			39
			11
			1
			32
			25
			35
			8
			17
			7
			9
			4
			2
			34
			10
			3
		""".trimIndent()
		val buckets = jolts.bucketJolts(jolts.parseInput(input))

		assertEquals(220, buckets[1]!! * buckets[3]!!)
	}

	@Test
	fun part2_example1() {
		val input = """
			16
			10
			15
			5
			1
			11
			7
			19
			6
			12
			4
		""".trimIndent()
		val combinations = jolts.countCombinations(jolts.parseInput(input))

		assertEquals(8, combinations)
	}

	@Test
	fun part2_example2() {
		val input = """
			28
			33
			18
			42
			31
			14
			46
			20
			48
			47
			24
			23
			49
			45
			19
			38
			39
			11
			1
			32
			25
			35
			8
			17
			7
			9
			4
			2
			34
			10
			3
		""".trimIndent()
		val combinations = jolts.countCombinations(jolts.parseInput(input))

		assertEquals(19208, combinations)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day10.txt").readText()
		val buckets = jolts.bucketJolts(jolts.parseInput(input))

		println("Jolt factor: ${buckets[1]!! * buckets[3]!!}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day10.txt").readText()
		val combinations = jolts.countCombinations(jolts.parseInput(input))

		println("Combinations: $combinations")
	}
}
