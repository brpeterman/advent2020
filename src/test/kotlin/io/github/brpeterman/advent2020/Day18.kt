package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day18 {
	@Test
	fun part1_example1() {
		val input = """1 + 2 * 3 + 4 * 5 + 6"""
		val total = NewMath.evaluateLtr(input)

		assertEquals(71, total)
	}

	@Test
	fun part1_example2() {
		val input = """1 + (2 * 3) + (4 * (5 + 6))"""
		val total = NewMath.evaluateLtr(input)

		assertEquals(51, total)
	}

	@Test
	fun part1_example3() {
		val input = """2 * 3 + (4 * 5)"""
		val total = NewMath.evaluateLtr(input)

		assertEquals(26, total)
	}

	@Test
	fun part1_example4() {
		val input = """5 + (8 * 3 + 9 + 3 * 4 * 3)"""
		val total = NewMath.evaluateLtr(input)

		assertEquals(437, total)
	}

	@Test
	fun part1_example5() {
		val input = """5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"""
		val total = NewMath.evaluateLtr(input)

		assertEquals(12240, total)
	}

	@Test
	fun part1_example6() {
		val input = """((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"""
		val total = NewMath.evaluateLtr(input)

		assertEquals(13632, total)
	}
	@Test
	fun part2_example1() {
		val input = """1 + 2 * 3 + 4 * 5 + 6"""
		val total = NewMath.evaluateWithPrecedence(input)

		assertEquals(231, total)
	}

	@Test
	fun part2_example2() {
		val input = """1 + (2 * 3) + (4 * (5 + 6))"""
		val total = NewMath.evaluateWithPrecedence(input)

		assertEquals(51, total)
	}

	@Test
	fun part2_example3() {
		val input = """2 * 3 + (4 * 5)"""
		val total = NewMath.evaluateWithPrecedence(input)

		assertEquals(46, total)
	}

	@Test
	fun part2_example4() {
		val input = """5 + (8 * 3 + 9 + 3 * 4 * 3)"""
		val total = NewMath.evaluateWithPrecedence(input)

		assertEquals(1445, total)
	}

	@Test
	fun part2_example5() {
		val input = """5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"""
		val total = NewMath.evaluateWithPrecedence(input)

		assertEquals(669060, total)
	}

	@Test
	fun part2_example6() {
		val input = """((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"""
		val total = NewMath.evaluateWithPrecedence(input)

		assertEquals(23340, total)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day18.txt").readText()
		val total = input.split("\n")
				.filter { it.isNotEmpty() }
				.map { NewMath.evaluateWithPrecedence(it) }
				.sum()

		println("Sum: $total")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day18.txt").readText()
		val total = input.split("\n")
				.filter { it.isNotEmpty() }
				.map { NewMath.evaluateWithPrecedence(it) }
				.sum()

		println("Sum: $total")
	}
}
