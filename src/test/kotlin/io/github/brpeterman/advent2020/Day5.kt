package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day5 {
	@Test
	fun part1_example1() {
		val input = """BFFFBBFRRR"""
		val assignments = SeatAssignments()
		val seatId = assignments.codeToSeat(input, 128, 8)

		assertEquals(567, seatId)
	}

	@Test
	fun part1_example2() {
		val input = """FFFBBBFRRR"""
		val assignments = SeatAssignments()
		val seatId = assignments.codeToSeat(input, 128, 8)

		assertEquals(119, seatId)
	}

	@Test
	fun part1_example3() {
		val input = """BBFFBBFRLL"""
		val assignments = SeatAssignments()
		val seatId = assignments.codeToSeat(input, 128, 8)

		assertEquals(820, seatId)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day5.txt").readText()
		val assignments = SeatAssignments()
		val maxSeatId = input.split("\n")
				.filter { it.isNotEmpty() }
				.map { assignments.codeToSeat(it, 128, 8) }
				.maxOrNull()

		println("Max seat ID: $maxSeatId")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day5.txt").readText()
		val assignments = SeatAssignments()
		val assignedSeats = input.split("\n")
				.filter { it.isNotEmpty() }
				.map { assignments.codeToSeat(it, 128, 8) }
				.toSet()

		var mySeat = -1
		for (seatId in (8..1018)) {
			if (!assignedSeats.contains(seatId) &&
					assignedSeats.contains(seatId + 1) &&
					assignedSeats.contains(seatId - 1)) {
				mySeat = seatId
				break
			}
		}

		println("My seat: $mySeat")
	}
}
