package io.github.brpeterman.advent2020

class SleddingSim(input: String) {
	val map: SleddingMap

	init {
		map = parseInput(input)
	}

	fun countTrees(slopeRight: Int, slopeDown: Int): Int {
		var position = Pair(0, 0)
		var trees = 0
		while (position.first < map.height) {
			position = Pair(position.first + slopeDown, (position.second + slopeRight) % map.width)
			if (map.tiles[position] == TileType.TREE) {
				trees++
			}
		}
		return trees
	}

	private fun parseInput(input: String): SleddingMap {
		val tiles = HashMap<Pair<Int, Int>, TileType>()
		val lines = input.split("\n")
		lines.filter { it.isNotEmpty() }
				.forEachIndexed { lineNum, line ->
					line.toCharArray()
							.forEachIndexed { charNum, char ->
								val tile = toTileType(char)
								if (tile != TileType.BLANK) {
									tiles[Pair(lineNum, charNum)] = tile
								}
							}
				}
		val height = lines.size
		val width = lines.first().length
		return SleddingMap(tiles, height, width)
	}

	private fun toTileType(tileInput: Char): TileType {
		return when (tileInput) {
			'.' -> TileType.BLANK
			'#' -> TileType.TREE
			else -> throw IllegalStateException("Unknown tile type: $tileInput")
		}
	}
}

class SleddingMap(val tiles: Map<Pair<Int, Int>, TileType>, val height: Int, val width: Int)

enum class TileType {
	BLANK,
	TREE
}
