package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day15 {
	val game = NumberGame()

	@Test
	fun part1_example1() {
		val lastNum = game.play(listOf(0,3,6).toMutableList(), 2020)

		assertEquals(436, lastNum)
	}

	@Test
	fun part1_example2() {
		val lastNum = game.play(listOf(1,3,2).toMutableList(), 2020)

		assertEquals(1, lastNum)
	}

	@Test
	fun part1_example3() {
		val lastNum = game.play(listOf(2,1,3).toMutableList(), 2020)

		assertEquals(10, lastNum)
	}

	@Test
	fun part1_example4() {
		val lastNum = game.play(listOf(1,2,3).toMutableList(), 2020)

		assertEquals(27, lastNum)
	}

	@Test
	fun part1_example5() {
		val lastNum = game.play(listOf(2,3,1).toMutableList(), 2020)

		assertEquals(78, lastNum)
	}

	@Test
	fun part1_example6() {
		val lastNum = game.play(listOf(3,2,1).toMutableList(), 2020)

		assertEquals(438, lastNum)
	}

	@Test
	fun part1_example7() {
		val lastNum = game.play(listOf(3,1,2).toMutableList(), 2020)

		assertEquals(1836, lastNum)
	}

	@Test
	fun part1_solve() {
		val lastNum = game.play(listOf(0,13,1,8,6,15).toMutableList(), 2020)

		println("2020th number: ${lastNum}")
	}

	@Test
	fun part2_solve() {
		val lastNum = game.play(listOf(0,13,1,8,6,15).toMutableList(), 30000000)

		println("30000000th number: ${lastNum}")
	}
}
