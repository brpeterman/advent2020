package io.github.brpeterman.advent2020

import kotlin.math.log2

class SeatAssignments {
	fun codeToSeat(code: String, rows: Int, cols: Int): Int {
		val splitPosition = log2(rows.toDouble()).toInt()
		val rowCode = code.substring(0, splitPosition)
		val colCode = code.substring(splitPosition)

		val row = findPosition(rowCode, rows)
		val col = findPosition(colCode, cols)

		return row * cols + col
	}

	fun findPosition(code: String, totalPositions: Int): Int {
		val nextChar = code.first()
		if (totalPositions == 2) {
			return when(nextChar) {
				'F', 'L' -> 0
				'B', 'R' -> 1
				else -> throw IllegalStateException("Unknown character: ${nextChar}")
			}
		}

		when (nextChar) {
			'F', 'L' -> return findPosition(code.substring(1), totalPositions / 2)
			'B', 'R' -> return totalPositions / 2 + findPosition(code.substring(1), totalPositions / 2)
			else -> throw IllegalStateException("Unknown character: ${nextChar}")
		}
	}
}
