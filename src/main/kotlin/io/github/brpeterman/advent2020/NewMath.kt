package io.github.brpeterman.advent2020

class NewMath {
	enum class Operator {
		PLUS,
		TIMES
	}

	companion object {
		fun evaluateLtr(expr: String): Long {
			val tokens = expr.replace("""[()]""".toRegex()) { match -> " ${match.value} " }
					.replace(""" +""".toRegex(), " ")
					.split(' ')
					.filter { it.isNotEmpty() }
					.toMutableList()
			return calculateLtr(tokens)
		}

		fun evaluateWithPrecedence(expr: String): Long {
			val tokens = expr.replace("""[()]""".toRegex()) { match -> " ${match.value} " }
					.replace(""" +""".toRegex(), " ")
					.split(' ')
					.filter { it.isNotEmpty() }
					.toMutableList()
			return calculateWithPrecedence(tokens)
		}

		private fun calculateLtr(tokens: MutableList<String>): Long {
			val valuesStack = ArrayList<Long>()
			var operator = Operator.PLUS
			while (tokens.isNotEmpty()) {
				val token = tokens.removeFirst()
				if (token == "(") {
					valuesStack.add(0, calculateLtr(tokens))
				} else if (token == ")") {
					return valuesStack.first()
				} else {
					when (token) {
						"+" -> operator = Operator.PLUS
						"*" -> operator = Operator.TIMES
						else -> valuesStack.add(token.toLong())
					}
				}
				if (valuesStack.size > 1) {
					val left = valuesStack.removeLast()
					val right = valuesStack.removeLast()
					valuesStack.add(
							when (operator) {
								Operator.PLUS -> left + right
								Operator.TIMES -> left * right
							})
				}
			}
			return valuesStack.last()
		}

		private fun calculateWithPrecedence(tokens: MutableList<String>): Long {
			val valuesStack = ArrayList<Long>()
			val operatorStack = ArrayList<Operator>()
			while (tokens.isNotEmpty()) {
				val token = tokens.removeFirst()
				if (token == "(") {
					valuesStack.add(calculateWithPrecedence(tokens))
				} else if (token == ")") {
					break
				} else {
					when (token) {
						"+" -> {
							operatorStack.add(Operator.PLUS)
							continue
						}
						"*" -> operatorStack.add(Operator.TIMES)
						else -> valuesStack.add(token.toLong())
					}
				}
				if (valuesStack.size > 1) {
					if (operatorStack.last() == Operator.PLUS) {
						operatorStack.removeLast()
						val right = valuesStack.removeLast()
						val left = valuesStack.removeLast()
						valuesStack.add(left + right)
					}
				}
			}
			for (i in operatorStack.indices) {
				val right = valuesStack.removeLast()
				val left = valuesStack.removeLast()
				valuesStack.add(left * right)
			}
			return valuesStack.last()
		}
	}
}
