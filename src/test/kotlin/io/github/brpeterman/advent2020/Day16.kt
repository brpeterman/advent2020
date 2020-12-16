package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day16 {
	@Test
	fun part1_example() {
		val input = """
			class: 1-3 or 5-7
			row: 6-11 or 33-44
			seat: 13-40 or 45-50

			your ticket:
			7,1,14

			nearby tickets:
			7,3,47
			40,4,50
			55,2,20
			38,6,12
		""".trimIndent()
		val checker = TrainTickets(input)
		val invalidValues = checker.findAllInvalidValues()

		assertEquals(71, invalidValues.sum())
	}

	@Test
	fun part2_example() {
		val input = """
			class: 0-1 or 4-19
			row: 0-5 or 8-19
			seat: 0-13 or 16-19

			your ticket:
			11,12,13

			nearby tickets:
			3,9,18
			15,1,5
			5,14,9
		""".trimIndent()
		val checker = TrainTickets(input)
		val fieldOrder = checker.calculateFieldOrder()

		println("Field order: ${fieldOrder.joinToString(", ")}")
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day16.txt").readText()
		val checker = TrainTickets(input)
		val invalidValues = checker.findAllInvalidValues()

		println("Sum of invalid values: ${invalidValues.sum()}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day16.txt").readText()
		val checker = TrainTickets(input)
		val fieldOrder = checker.calculateFieldOrder()

		val product = fieldOrder.withIndex()
				.filter { (_, attribute) -> attribute.startsWith("departure") }
				.map { (index, _) -> index }
				.fold(1L) { product, index -> product * checker.yourTicket[index].toLong() }

		println("Product: $product")
	}
}
