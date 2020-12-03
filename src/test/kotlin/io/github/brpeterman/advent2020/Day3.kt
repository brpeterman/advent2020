import io.github.brpeterman.advent2020.SleddingSim
import kotlin.test.Test
import kotlin.test.assertEquals

class Day3 {
	@Test
	fun part1_example() {
		val input = """
			..##.......
			#...#...#..
			.#....#..#.
			..#.#...#.#
			.#...##..#.
			..#.##.....
			.#.#.#....#
			.#........#
			#.##...#...
			#...##....#
			.#..#...#.#
		""".trimIndent()
		val sim = SleddingSim(input)
		assertEquals(7, sim.countTrees(3, 1))
	}

	@Test
	fun part2_example() {
		val input = """
			..##.......
			#...#...#..
			.#....#..#.
			..#.#...#.#
			.#...##..#.
			..#.##.....
			.#.#.#....#
			.#........#
			#.##...#...
			#...##....#
			.#..#...#.#
		""".trimIndent()
		val sim = SleddingSim(input)
		val trees1 = sim.countTrees(1, 1)
		val trees2 = sim.countTrees(3, 1)
		val trees3 = sim.countTrees(5, 1)
		val trees4 = sim.countTrees(7, 1)
		val trees5 = sim.countTrees(1, 2)

		assertEquals(336, trees1 * trees2 * trees3 * trees4 * trees5)
	}

	@Test
	fun part1_solve() {
		val input = javaClass.getResource("/day3.txt").readText()
		val sim = SleddingSim(input)

		val trees = sim.countTrees(3, 1)
		println("Trees: $trees")
	}

	@Test
	fun part2_solve() {
		val input = javaClass.getResource("/day3.txt").readText()
		val sim = SleddingSim(input)

		val trees1 = sim.countTrees(1, 1).toLong()
		val trees2 = sim.countTrees(3, 1)
		val trees3 = sim.countTrees(5, 1)
		val trees4 = sim.countTrees(7, 1)
		val trees5 = sim.countTrees(1, 2)
		println("Trees multiplied: ${trees1 * trees2 * trees3 * trees4 * trees5}")
	}
}
