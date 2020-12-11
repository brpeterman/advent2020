package io.github.brpeterman.advent2020

import java.util.*
import kotlin.collections.HashMap
import kotlin.collections.HashSet

class SeatingSim {
	enum class ChoosingScheme {
		PART_1,
		PART_2
	}

	fun parseInput(input: String): Layout {
		val lines = input.split("\n")
		val layout = Layout(lines[0].length, lines.size)
		lines.filter { it.isNotEmpty() }
				.forEachIndexed { lineNum, line ->
					line.toCharArray()
							.forEachIndexed { colNum, char ->
								var seatState: Layout.State? = null
								when (char) {
									'L' -> seatState = Layout.State.EMPTY
									'#' -> seatState = Layout.State.OCCUPIED
									'.' -> {}
									else -> throw IllegalStateException("Unknown seat char: $char")
								}
								if (seatState != null) {
									layout.positions[lineNum to colNum] = seatState
								}
							}
				}
		return layout
	}

	fun findStableLayout(initialLayout: Layout, scheme: ChoosingScheme): Layout {
		val knownLayouts = HashSet<Layout>()
		var currentLayout = initialLayout
		while (!knownLayouts.contains(currentLayout)) {
			knownLayouts.add(currentLayout)
			currentLayout = calculateNext(currentLayout, scheme)
		}
		return currentLayout
	}

	fun calculateNext(currentLayout: Layout, scheme: ChoosingScheme): Layout {
		val newLayout = Layout(currentLayout.width, currentLayout.height)
		currentLayout.positions.forEach { position, state ->
			if (scheme == ChoosingScheme.PART_1) {
				newLayout.positions[position] = transitionPart1(position, currentLayout)
			} else {
				newLayout.positions[position] = transitionPart2(position, currentLayout)
			}
		}
		return newLayout
	}

	fun transitionPart1(seat: Pair<Int, Int>, layout: Layout): Layout.State {
		val positions = layout.positions
		val originalState = positions[seat]!!
		val occupiedNeighbors = listOf(
				positions[seat.first - 1 to seat.second - 1],
				positions[seat.first - 1 to seat.second],
				positions[seat.first - 1 to seat.second + 1],
				positions[seat.first to seat.second - 1],
				positions[seat.first to seat.second + 1],
				positions[seat.first + 1 to seat.second -1],
				positions[seat.first + 1 to seat.second],
				positions[seat.first + 1 to seat.second + 1])
				.count { it == Layout.State.OCCUPIED }
		return when (occupiedNeighbors) {
			0 -> Layout.State.OCCUPIED
			in (4..8) -> if (originalState == Layout.State.OCCUPIED) Layout.State.EMPTY else originalState
			else -> originalState
		}
	}

	fun transitionPart2(seat: Pair<Int, Int>, layout: Layout): Layout.State {
		val positions = layout.positions
		val originalState = positions[seat]!!
		var occupiedNeighbors = 0
		for (offset in listOf(
				-1 to -1,
				-1 to 0,
				-1 to 1,
				0 to -1,
				0 to 1,
				1 to -1,
				1 to 0,
				1 to 1)) {
			val distToEdge = listOf(
					seat.first * (-1 * offset.first),
					seat.second * (-1 * offset.second),
					(layout.height - seat.first - 1) * offset.first,
					(layout.width - seat.second - 1) * offset.second)
					.maxOrNull()!!
			for (dist in (1..distToEdge)) {
				val checkRow = seat.first + (dist * offset.first)
				val checkCol = seat.second + (dist * offset.second)
				if (positions[checkRow to checkCol] == Layout.State.OCCUPIED) {
					occupiedNeighbors++
					break
				} else if (positions[checkRow to checkCol] == Layout.State.EMPTY) {
					break
				}
			}
			if (occupiedNeighbors >= 5) {
				break
			}
		}
		return when (occupiedNeighbors) {
			0 -> Layout.State.OCCUPIED
			in (5..8) -> if (originalState == Layout.State.OCCUPIED) Layout.State.EMPTY else originalState
			else -> originalState
		}
	}
}

class Layout(val width: Int, val height: Int) {
	enum class State {
		EMPTY,
		OCCUPIED
	}

	val positions = HashMap<Pair<Int, Int>, State>()

	override fun hashCode(): Int {
		return Objects.hashCode(positions)
	}

	override fun equals(other: Any?): Boolean {
		if (other is Layout) {
			return positions.equals(other.positions)
		}
		return false
	}
}
