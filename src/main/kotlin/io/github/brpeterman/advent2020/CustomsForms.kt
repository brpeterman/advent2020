package io.github.brpeterman.advent2020

class CustomsForms {
	fun countOr(groupInput: String): Int {
		val answers = HashSet<Char>()
		groupInput.split("\n")
				.filter { it.isNotEmpty() }
				.forEach { line ->
					line.toCharArray()
							.forEach { answers.add(it) }
				}
		return answers.size
	}

	fun countAnd(groupInput: String): Int {
		val answers = HashMap<Char, Int>()
		val lines = groupInput.split("\n")
				.filter { it.isNotEmpty() }
		val partySize = lines.size
		lines.forEach { line ->
			line.toCharArray()
					.forEach { q -> answers.put(q, answers.getOrDefault(q, 0) + 1)}
		}
		return answers.count { it.value == partySize }
	}
}
