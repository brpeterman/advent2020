package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day1 {
	@Test
	fun part1_example() {
		val input = """
			1721
			979
			366
			299
			675
			1456
		""".trimIndent()
		val expenses = Expenses(input)
		assertEquals(514579, expenses.findExpensePair())
	}

	@Test
	fun part2_example() {
		val input = """
			1721
			979
			366
			299
			675
			1456
		""".trimIndent()
		val expenses = Expenses(input)
		assertEquals(241861950, expenses.findExpenseTriple())
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day1.txt").readText()
		val expenses = Expenses(input)
		val expenseValue = expenses.findExpensePair()

		println("Value: $expenseValue")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day1.txt").readText()
		val expenses = Expenses(input)
		val expenseValue = expenses.findExpenseTriple()

		println("Value: $expenseValue")
	}
}
