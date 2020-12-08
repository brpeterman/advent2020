package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day8 {
	@Test
	fun part1_example() {
		val input = """
			nop +0
			acc +1
			jmp +4
			acc +3
			jmp -3
			acc -99
			acc +1
			jmp -4
			acc +6
		""".trimIndent()
		val computer = Computer(input)
		computer.execute(buildNoRepeatCondition())

		assertEquals(5, computer.accumulator)
	}

	@Test
	fun part2_example() {
		val input = """
			nop +0
			acc +1
			jmp +4
			acc +3
			jmp -3
			acc -99
			acc +1
			jmp -4
			acc +6
		""".trimIndent()
		val output = findCorrectedOutput(input)

		assertEquals(8, output)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day8.txt").readText()
		val computer = Computer(input)
		computer.execute(buildNoRepeatCondition())

		println("Accumulator: ${computer.accumulator}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day8.txt").readText()
		val output = findCorrectedOutput(input)

		println("Accumulator: ${output}")
	}
}
