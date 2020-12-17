package io.github.brpeterman.advent2020

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet
import kotlin.math.max
import kotlin.math.min

class ConwayCubes(input: String) {
	var activeCells = HashSet<Cell>()
	private var rangeX: IntRange
	private var rangeY: IntRange
	private var rangeZ = 0..0
	private var rangeW = 0..0

	init {
		val lines = input.split("\n")
				.filter { it.isNotEmpty() }
		rangeY = lines.indices
		rangeX = lines.first().indices
		lines.forEachIndexed { y, line ->
			line.toCharArray()
					.forEachIndexed { x, char ->
						if (char == '#') {
							activeCells.add(Cell(x, y, 0, 0))
						}
					}
		}
	}

	fun simulate3d(cycles: Int) {
		for (cycle in (0 until cycles)) {
			cycle(OFFSETS_3D)
		}
	}

	fun simulate4d(cycles: Int) {
		for (cycle in (0 until cycles)) {
			cycle(OFFSETS_4D)
		}
	}

	private fun cycle(offsets: List<Cell>) {
		var minX = 0
		var maxX = 0
		var minY = 0
		var maxY = 0
		var minZ = 0
		var maxZ = 0
		var minW = 0
		var maxW = 0
		val newActiveCells = HashSet<Cell>()
		for (x in (rangeX.first - 1) .. (rangeX.last + 1)) {
			for (y in (rangeY.first - 1) .. (rangeY.last + 1)) {
				for (z in (rangeZ.first - 1) .. (rangeZ.last + 1)) {
					for (w in (rangeW.first - 1) .. (rangeW.last + 1)) {
						transitionCell(Cell(x, y, z, w), newActiveCells, offsets)
						val active = newActiveCells.contains(Cell(x, y, z, w))
						if (active) {
							minX = min(x, minX)
							maxX = max(x, maxX)
							minY = min(y, minY)
							maxY = max(y, maxY)
							minZ = min(z, minZ)
							maxZ = max(z, maxZ)
							minW = min(w, minW)
							maxW = max(w, maxW)
						}
					}
				}
			}
		}
		activeCells = newActiveCells
		rangeX = minX..maxX
		rangeY = minY..maxY
		rangeZ = minZ..maxZ
		rangeW = minW..maxW
	}

	private fun transitionCell(cell: Cell, newActiveCells: MutableSet<Cell>, offsets: List<Cell>) {
		val neighbors = offsets
				.map { offset -> Cell(cell.x + offset.x, cell.y + offset.y, cell.z + offset.z, cell.w + offset.w) }
				.count { neighborCell -> activeCells.contains(neighborCell) }
		if (activeCells.contains(cell)) {
			if ((2..3).contains(neighbors)) {
				newActiveCells.add(cell)
			}
		} else if (neighbors == 3) {
			newActiveCells.add(cell)
		}
	}

	companion object {
		val OFFSETS_3D = constructOffsets3D()
		val OFFSETS_4D = constructOffsets4D()

		private fun constructOffsets3D(): List<Cell> {
			val offsets = ArrayList<Cell>()
			for (x in -1..1) {
				for (y in -1..1) {
					for (z in -1..1) {
						if (x == 0 && y == 0 && z == 0) {
							continue
						}
						offsets.add(Cell(x, y, z, 0))
					}
				}
			}
			return offsets
		}

		private fun constructOffsets4D(): List<Cell> {
			val offsets = ArrayList<Cell>()
			for (x in -1..1) {
				for (y in -1..1) {
					for (z in -1..1) {
						for (w in -1..1) {
							if (x == 0 && y == 0 && z == 0 && w == 0) {
								continue
							}
							offsets.add(Cell(x, y, z, w))
						}
					}
				}
			}
			return offsets
		}
	}
}

class Cell(val x: Int, val y: Int, val z: Int, val w: Int) {
	override fun equals(other: Any?): Boolean {
		if (other is Cell) {
			return x == other.x && y == other.y && z == other.z && w == other.w
		}
		return false
	}

	override fun hashCode(): Int {
		return Objects.hash(x, y, z, w)
	}
}
