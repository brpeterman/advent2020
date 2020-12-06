package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day6 {
	val forms = CustomsForms()

	@Test
	fun part1_example() {
		val input = """
			abc

			a
			b
			c

			ab
			ac

			a
			a
			a
			a

			b
		""".trimIndent()
		val groups = input.split("\n\n")
		val sum = groups.fold(0, { sum, groupInput -> sum + forms.countOr(groupInput) })

		assertEquals(11, sum)
	}

	@Test
	fun part2_example() {
		val input = """
			abc

			a
			b
			c

			ab
			ac

			a
			a
			a
			a

			b
		""".trimIndent()
		val groups = input.split("\n\n")
		val sum = groups.fold(0, { sum, groupInput -> sum + forms.countAnd(groupInput) })

		assertEquals(6, sum)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day6.txt").readText()
		val groups = input.split("\n\n")
		val sum = groups.fold(0, { sum, groupInput -> sum + forms.countOr(groupInput) })

		println("Sum: $sum")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day6.txt").readText()
		val groups = input.split("\n\n")
		val sum = groups.fold(0, { sum, groupInput -> sum + forms.countAnd(groupInput) })

		println("Sum: $sum")
	}
}
