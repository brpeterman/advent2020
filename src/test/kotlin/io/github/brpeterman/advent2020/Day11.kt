package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11 {
	val sim = SeatingSim()

	@Test
	fun part1_example() {
		val input = """
			L.LL.LL.LL
			LLLLLLL.LL
			L.L.L..L..
			LLLL.LL.LL
			L.LL.LL.LL
			L.LLLLL.LL
			..L.L.....
			LLLLLLLLLL
			L.LLLLLL.L
			L.LLLLL.LL
		""".trimIndent()
		val initial = sim.parseInput(input)
		val final = sim.findStableLayout(initial, SeatingSim.ChoosingScheme.PART_1)
		val occupied = final.positions.values
				.count { it == Layout.State.OCCUPIED }

		assertEquals(37, occupied)
	}

	@Test
	fun part2_example() {
		val input = """
			L.LL.LL.LL
			LLLLLLL.LL
			L.L.L..L..
			LLLL.LL.LL
			L.LL.LL.LL
			L.LLLLL.LL
			..L.L.....
			LLLLLLLLLL
			L.LLLLLL.L
			L.LLLLL.LL
		""".trimIndent()
		val initial = sim.parseInput(input)
		val final = sim.findStableLayout(initial, SeatingSim.ChoosingScheme.PART_2)
		val occupied = final.positions.values
				.count { it == Layout.State.OCCUPIED }

		assertEquals(26, occupied)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day11.txt").readText()
		val initial = sim.parseInput(input)
		val final = sim.findStableLayout(initial, SeatingSim.ChoosingScheme.PART_1)
		val occupied = final.positions.values
				.count { it == Layout.State.OCCUPIED }

		println("Final occupied seats: $occupied")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day11.txt").readText()
		val initial = sim.parseInput(input)
		val final = sim.findStableLayout(initial, SeatingSim.ChoosingScheme.PART_2)
		val occupied = final.positions.values
				.count { it == Layout.State.OCCUPIED }

		println("Final occupied seats: $occupied")
	}
}
