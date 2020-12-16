package io.github.brpeterman.advent2020

class TrainTickets(input: String) {
	val rules: Map<String, List<IntRange>>
	val otherTickets: List<List<Int>>
	val yourTicket: List<Int>

	init {
		val (rulesInput, yourTicketInput, otherTicketsInput) = input.split("\n\n")
		rules = parseRules(rulesInput)
		yourTicket = parseTicket(yourTicketInput.split("\n")[1])
		otherTickets = otherTicketsInput.split("\n")
				.filter { it.isNotEmpty() }
				.drop(1)
				.map { parseTicket(it) }
	}

	fun findAllInvalidValues(): List<Int> {
		val invalidValues = ArrayList<Int>()
		for (ticket in otherTickets) {
			val invalidValue = findInvalidValue(ticket)
			if (invalidValue != null) {
				invalidValues.add(invalidValue)
			}
		}
		return invalidValues
	}

	fun findInvalidValue(ticket: List<Int>): Int? {
		for (value in ticket) {
			val isValid = rules.values
					.flatMap { it }
					.any { range -> range.contains(value) }
			if (!isValid) {
				return value
			}
		}
		return null
	}

	fun calculateFieldOrder(): List<String> {
		val possibilities = HashMap<String, MutableSet<Int>>()
		val fields = yourTicket.size
		rules.keys.forEach { attribute -> possibilities[attribute] = (0..fields-1).toSet().toMutableSet() }
		val validTickets = otherTickets.filter { findInvalidValue(it) == null }
		var fieldToCheck = 0
		while (possibilities.values.any { it.size > 1 }) {
			for (ticket in validTickets) {
				for ((attribute, ranges) in rules) {
					if (possibilities[attribute]!!.size > 1) {
						if (!ranges.any { range -> range.contains(ticket[fieldToCheck]) }) {
							possibilities[attribute]!!.remove(fieldToCheck)
						}
					}
					if (possibilities[attribute]!!.size == 1) {
						for (otherAttribute in possibilities.keys) {
							if (otherAttribute != attribute) {
								possibilities[otherAttribute]!!.remove(possibilities[attribute]!!.first())
							}
						}
					}
				}
			}
			fieldToCheck = (fieldToCheck + 1) % fields
		}
		return possibilities.entries
				.sortedBy { it.value.first() }
				.map { it.key }
	}

	companion object {
		fun parseRules(input: String): Map<String, List<IntRange>> {
			val pattern = """\A([a-z ]+): (\d+)-(\d+) or (\d+)-(\d+)\Z""".toRegex()
			return input.split("\n")
					.filter { it.isNotEmpty() }
					.map { line ->
						val matches = pattern.find(line)
						if (matches == null) {
							throw IllegalStateException("Invalid rule line: $line")
						}
						val (attribute, rangeStart1, rangeEnd1, rangeStart2, rangeEnd2) = matches.destructured
						attribute to listOf(rangeStart1.toInt()..rangeEnd1.toInt(), rangeStart2.toInt()..rangeEnd2.toInt())
					}
					.toMap()
		}

		fun parseTicket(input: String): List<Int> {
			return input.split(",")
					.map { it.toInt() }
		}
	}
}
