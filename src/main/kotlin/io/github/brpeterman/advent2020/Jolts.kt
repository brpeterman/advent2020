package io.github.brpeterman.advent2020

class Jolts {
	fun parseInput(input: String): List<Int> {
		val adapters = input.split("\n")
				.filter { it.isNotEmpty() }
				.map { it.toInt() }
				.sorted()
				.toMutableList()
		adapters.add(0, 0)
		return adapters
	}

	fun bucketJolts(adapters: List<Int>): Map<Int, Int> {
		val buckets = HashMap<Int, Int>().withDefault { 0 }
		buckets[3] = 1 // The final adapter-to-device jump
		adapters.forEachIndexed { index, currentAdapter ->
			val diff: Int
			if (index == 0) {
				diff = currentAdapter
			} else {
				diff = currentAdapter - adapters[index - 1]
			}
			if (diff > 3) {
				throw IllegalStateException("Invalid jolt jump: ${diff} between ${adapters[index - 1]} and ${currentAdapter}")
			}
			buckets[diff] = buckets.getValue(diff) + 1
		}
		return buckets
	}

	fun countCombinations(adapters: List<Int>): Long {
		val memo = HashMap<Int, Long>()
		for (index in (1..adapters.size-1)) {
			pathsTo(index, adapters, memo)
		}
		return memo[adapters.size - 1]!!
	}

	private fun pathsTo(index: Int, adapters: List<Int>, memo: MutableMap<Int, Long>): Long {
		if (index == 0) {
			return 1
		}
		if (memo[index] != null) {
			return memo[index]!!
		}
		var paths: Long = 0
		val currentAdapter = adapters[index]
		for (offset in (1..3)) {
			if (index >= offset) {
				val diff = currentAdapter - adapters[index - offset]
				if (diff <= 3) {
					paths += pathsTo(index - offset, adapters, memo)
				}
			}
		}
		memo[index] = paths
		memo.remove(index - 3)
		return paths
	}
}
