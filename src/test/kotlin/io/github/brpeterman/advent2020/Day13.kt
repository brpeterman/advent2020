package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day13 {
	val schedule = BusSchedule()

	@Test
	fun part1_example() {
		val input = """
			939
			7,13,x,x,59,x,31,19
		""".trimIndent()
		val lines = input.split("\n")
		val arrivalTime = lines[0].toLong()
		val buses = lines[1].split(",")
				.filter { it != "x" }
				.map { it.toInt() }
		val output = schedule.earliestDeparture(arrivalTime, buses)

		assertEquals(295, output)
	}

	@Test
	fun part2_example1() {
		val input = """
			939
			7,13,x,x,59,x,31,19
		""".trimIndent()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		assertEquals(1068781, magicTimestamp)
	}

	@Test
	fun part2_example2() {
		val input = """
			0
			17,x,13,19
		""".trimIndent()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		assertEquals(3417, magicTimestamp)
	}

	@Test
	fun part2_example3() {
		val input = """
			0
			67,7,59,61
		""".trimIndent()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		assertEquals(754018, magicTimestamp)
	}

	@Test
	fun part2_example4() {
		val input = """
			0
			67,x,7,59,61
		""".trimIndent()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		assertEquals(779210, magicTimestamp)
	}

	@Test
	fun part2_example5() {
		val input = """
			0
			67,7,x,59,61
		""".trimIndent()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		assertEquals(1261476, magicTimestamp)
	}

	@Test
	fun part2_example6() {
		val input = """
			0
			1789,37,47,1889
		""".trimIndent()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		assertEquals(1202161486, magicTimestamp)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day13.txt").readText()
		val lines = input.split("\n")
		val arrivalTime = lines[0].toLong()
		val buses = lines[1].split(",")
				.filter { it != "x" }
				.map { it.toInt() }
		val output = schedule.earliestDeparture(arrivalTime, buses)

		println("Output: $output")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day13.txt").readText()
		val lines = input.split("\n")
		val buses = lines[1].split(",")
				.withIndex()
				.filter { it.value != "x" }
				.map { Bus(it.value.toLong(), it.index.toLong()) }
		val magicTimestamp = schedule.findMagicTimestamp(buses)

		println("Magic timestamp: $magicTimestamp")
	}
}
