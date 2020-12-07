package io.github.brpeterman.advent2020

class BagRules(input: String) {
	val rules = HashMap<String, Rule>()

	init {
		parseInput(input)
	}

	fun findContainers(bagType: String): Set<String> {
		val rule = rules[bagType]
		if (rule == null || rule.containers.isEmpty()) {
			return setOf()
		}

		val reachable = HashSet<String>()
		rule.containers.forEach { container ->
			reachable.add(container.first)
			reachable.addAll(findContainers(container.first))
		}
		return reachable
	}

	fun countNested(bagType: String): Int {
		val rule = rules[bagType]
		if (rule == null || rule.contains.isEmpty()) {
			return 0
		}

		var numContained = 0
		rule.contains.forEach { contained ->
			numContained += contained.second + contained.second * countNested(contained.first)
		}
		return numContained
	}

	private fun parseInput(input: String) {
		val containerExpression = """\A([a-z ]+) bags contain(.+)\Z""".toRegex()
		val bagsExpression = """ (\d+) ([a-z ]+) bags?[,.]""".toRegex()

		input.split("\n")
				.filter { it.isNotEmpty() }
				.forEach { line ->
					val containerMatches = containerExpression.find(line)
					if (containerMatches != null) {
						val (bagType, bagsSpec) = containerMatches.destructured
						val parentBagRule = rules.getOrPut(bagType) { Rule() }
						bagsExpression.findAll(bagsSpec)
								.iterator()
								.forEach { match ->
									val (amount, subType) = match.destructured
									val childBagRule = rules.getOrPut(subType) { Rule() }
									childBagRule.containers.add(Pair(bagType, amount.toInt()))
									parentBagRule.contains.add(Pair(subType, amount.toInt()))
								}
					}
				}
	}
}

class Rule {
	val containers = HashSet<Pair<String, Int>>()
	val contains = HashSet<Pair<String, Int>>()
}
