package io.github.brpeterman.advent2020

class Passwords {
	fun ingestPasswords(input: String): List<Pair<PasswordRule, String>> {
		return input.split("\n")
				.filter { it.isNotEmpty() }
				.map { inputString ->
					val (ruleDef, password) = inputString.split(": ")
					val (rangeInput, char) = ruleDef.split(" ")
					val (rangeStart, rangeEnd) = rangeInput.split("-")
							.map { it.toInt() }
					val rule = PasswordRule(rangeStart.rangeTo(rangeEnd), char[0])
					Pair(rule, password)
				}
	}

	fun countPart1ValidPasswords(passwords: List<Pair<PasswordRule, String>>): Int {
		return passwords.fold(0, { sum, entry ->
			sum + if (isValidPart1(entry.first, entry.second)) 1 else 0
		})
	}

	fun isValidPart1(rule: PasswordRule, password: String): Boolean {
		val charCount = password.toCharArray()
				.filter { it == rule.char }
				.count()
		return rule.range.contains(charCount)
	}

	fun countPart2ValidPasswords(passwords: List<Pair<PasswordRule, String>>): Int {
		return passwords.fold(0, { sum, entry ->
			sum + if (isValidPart2(entry.first, entry.second)) 1 else 0
		})
	}

	fun isValidPart2(rule: PasswordRule, password: String): Boolean {
		val start = rule.range.start - 1
		val end = rule.range.endInclusive - 1
		return (password[start] == rule.char && password[end] != rule.char) ||
				(password[start] != rule.char && password[end] == rule.char)
	}
}

class PasswordRule(val range: IntRange, val char: Char)
