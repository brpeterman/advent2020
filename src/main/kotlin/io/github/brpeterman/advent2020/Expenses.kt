package io.github.brpeterman.advent2020

class Expenses(input: String) {
	private var expenseLines = HashMap<Long, Boolean>()

	init {
		val inputValues = input.split("\n")
				.filter { it.isNotEmpty() }
				.map { it.toLong() }
		inputValues.forEach {
			expenseLines[it] = true
		}
	}

	fun findExpensePair(): Long {
		expenseLines.forEach {
			val complement = 2020 - it.key
			if (expenseLines[complement] == true && it.key != complement) {
				println("Pair: ${it.key}, $complement")
				return it.key * complement
			}
		}
		throw IllegalStateException("No solution found")
	}

	fun findExpenseTriple(): Long {
		// Memoize the sums of every pair
		val partials = HashMap<Long, Pair<Long, Long>>()
		val linesList = expenseLines.keys.toList()
		for (i in (0..linesList.size - 1)) {
			for (j in (i+1..linesList.size - 1)) {
				val sum = linesList[i] + linesList[j]
				if (sum < 2020) {
					partials[sum] = Pair(linesList[i], linesList[j])
				}
			}
		}

		// Look for a complement among the memoized sums
		expenseLines.keys.forEach {
			val complement = 2020 - it
			if (partials[complement] != null) {
				val partial = partials[complement]!!
				if (partial.first != it && partial.second != it) {
					println("Triple: $it, ${partial.first}, ${partial.second}")
					return it * partial.first * partial.second
				}
			}
		}
		throw IllegalStateException("No solution found")
	}
}
