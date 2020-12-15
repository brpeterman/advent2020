package io.github.brpeterman.advent2020

class NumberGame {
	fun play(numbers: MutableList<Int>, maxTurn: Int): Int {
		val lastSeen = HashMap<Int, Int>()
		numbers.forEachIndexed { index, num -> lastSeen[num] = index + 1 }
		for (turn in (numbers.size+1..maxTurn)) {
			val lastNum = numbers.last()
			var newNum: Int
			if (lastSeen[lastNum] == null || lastSeen[lastNum] == turn - 1) {
				newNum = 0
			} else {
				newNum = turn - 1 - lastSeen[lastNum]!!
			}
			lastSeen[lastNum] = turn - 1
			numbers.add(newNum)
		}
		return numbers.last()
	}
}
