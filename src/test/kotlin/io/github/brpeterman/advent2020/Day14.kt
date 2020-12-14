package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day14 {
	@Test
	fun part1_example() {
		val input = """
			mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X
			mem[8] = 11
			mem[7] = 101
			mem[8] = 0
		""".trimIndent()
		val computer = MaskingComputer(input)
		val sum = computer.memory.values.sum()

		assertEquals(165, sum)
	}

	@Test
	fun part2_example() {
		val input = """
			mask = 000000000000000000000000000000X1001X
			mem[42] = 100
			mask = 00000000000000000000000000000000X0XX
			mem[26] = 1
		""".trimIndent()
		val computer = MaskingComputer(input, 2)
		val sum = computer.memory.values.sum()

		assertEquals(208, sum)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day14.txt").readText()
		val computer = MaskingComputer(input)
		val sum = computer.memory.values.sum()

		println("Memory entries: ${computer.memory.size}")
		println("Sum: $sum")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day14.txt").readText()
		val computer = MaskingComputer(input, 2)
		val sum = computer.memory.values.sum()

		println("Memory entries: ${computer.memory.size}")
		println("Sum: $sum")
	}
}
