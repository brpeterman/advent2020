package io.github.brpeterman.advent2020

import kotlin.test.Test
import kotlin.test.assertEquals

class Day7 {
	@Test
	fun part1_example() {
		val input = """
			light red bags contain 1 bright white bag, 2 muted yellow bags.
			dark orange bags contain 3 bright white bags, 4 muted yellow bags.
			bright white bags contain 1 shiny gold bag.
			muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
			shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
			dark olive bags contain 3 faded blue bags, 4 dotted black bags.
			vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
			faded blue bags contain no other bags.
			dotted black bags contain no other bags.
		""".trimIndent()
		val bagRules = BagRules(input)

		val containers = bagRules.findContainers("shiny gold")
		assertEquals(4, containers.size)
	}

	@Test
	fun part2_example1() {
		val input = """
			light red bags contain 1 bright white bag, 2 muted yellow bags.
			dark orange bags contain 3 bright white bags, 4 muted yellow bags.
			bright white bags contain 1 shiny gold bag.
			muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
			shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
			dark olive bags contain 3 faded blue bags, 4 dotted black bags.
			vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
			faded blue bags contain no other bags.
			dotted black bags contain no other bags.
		""".trimIndent()
		val bagRules = BagRules(input)

		val contained = bagRules.countNested("shiny gold")
		assertEquals(32, contained)
	}

	@Test
	fun part2_example2() {
		val input = """
			shiny gold bags contain 2 dark red bags.
			dark red bags contain 2 dark orange bags.
			dark orange bags contain 2 dark yellow bags.
			dark yellow bags contain 2 dark green bags.
			dark green bags contain 2 dark blue bags.
			dark blue bags contain 2 dark violet bags.
			dark violet bags contain no other bags.
		""".trimIndent()
		val bagRules = BagRules(input)

		val contained = bagRules.countNested("shiny gold")
		assertEquals(126, contained)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day7.txt").readText()
		val bagRules = BagRules(input)

		val containers = bagRules.findContainers("shiny gold")

		println("Containers: ${containers.size}")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day7.txt").readText()
		val bagRules = BagRules(input)

		val containers = bagRules.countNested("shiny gold")

		println("Nested: $containers")
	}
}
