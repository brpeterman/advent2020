package io.github.brpeterman.advent2020

class XmasCracker {
	fun findInvalidValue(preambleSize: Int, values: List<Long>): Long {
		val sums = seedSums(preambleSize, values)
		var index = preambleSize
		while (index < values.size) {
			calculateSums(preambleSize, values, sums, index)
			if (!isValid(preambleSize, values, sums, index)) {
				return values[index]
			}
			index++
		}
		throw IllegalStateException("Failed to find invalid value")
	}

	fun findWeakness(targetValue: Long, values: List<Long>): Long {
		for (rangeSize in (2..values.size-1)) {
			for (startIndex in (0..values.size-rangeSize)) {
				val sublist = values.slice(startIndex..startIndex+rangeSize-1)
				if (sublist.sum() == targetValue) {
					val min = sublist.minOrNull()!!
					val max = sublist.maxOrNull()!!
					return min + max
				}
			}
		}
		throw IllegalStateException("Failed to find weakness")
	}

	private fun isValid(preambleSize: Int, values: List<Long>, sums: MutableMap<Int, MutableMap<Int, Long>>, index: Int): Boolean {
		for (i in (index - preambleSize)..(index-1)) {
			val indexSums = sums.getOrPut(i) { HashMap() }
			if (indexSums.values.contains(values[index])) {
				return true
			}
		}
		return false
	}

	fun parseInput(input: String): List<Long> {
		return input.split("\n")
				.filter { it.isNotEmpty() }
				.map { it.toLong() }
	}

	private fun seedSums(preambleSize: Int, values: List<Long>): MutableMap<Int, MutableMap<Int, Long>> {
		val sums = HashMap<Int, MutableMap<Int, Long>>()
		for (i in (0..preambleSize-1)) {
			val indexSums = sums.getOrPut(i) { HashMap() }
			for (j in (i+1..preambleSize-1)) {
				indexSums[j] = values[i] + values[j]
			}
		}
		return sums
	}

	private fun calculateSums(preambleSize: Int, values: List<Long>, sums: MutableMap<Int, MutableMap<Int, Long>>, index: Int) {
		sums.remove(index - preambleSize - 2)
		for (i in (index - preambleSize)..(index-1)) {
			val indexSums = sums.getOrPut(i) { HashMap() }
			indexSums[index] = values[i] + values[index]
		}
	}
}
