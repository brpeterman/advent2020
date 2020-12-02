package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day2 {
	val validator = Passwords()

	@Test
	fun part1_example() {
		val input = """
			1-3 a: abcde
			1-3 b: cdefg
			2-9 c: ccccccccc
		""".trimIndent()
		val passwords = validator.ingestPasswords(input)
		assertEquals(2, validator.countPart1ValidPasswords(passwords))
	}

	@Test
	fun part2_example() {
		val input = """
			1-3 a: abcde
			1-3 b: cdefg
			2-9 c: ccccccccc
		""".trimIndent()
		val passwords = validator.ingestPasswords(input)
		assertEquals(1, validator.countPart2ValidPasswords(passwords))
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day2.txt").readText()
		val passwords = validator.ingestPasswords(input)

		println("Valid passwords: ${validator.countPart1ValidPasswords(passwords)}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day2.txt").readText()
		val passwords = validator.ingestPasswords(input)

		println("Valid passwords: ${validator.countPart2ValidPasswords(passwords)}")
	}
}
