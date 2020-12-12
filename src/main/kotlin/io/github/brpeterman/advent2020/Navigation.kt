package io.github.brpeterman.advent2020

class Navigation {
	var shipPosition = 0 to 0
	var waypoint = 10 to -1

	fun parseInput(input: String): List<Pair<Char, Int>> {
		return input.split("\n")
				.filter { it.isNotEmpty() }
				.map { line ->
					val direction = line[0]
					val magnitude = line.substring(1).toInt()
					direction to magnitude
				}
	}

	fun navigate(instructions: List<Pair<Char, Int>>): Pair<Int, Int> {
		var orientation = Direction.EAST
		for (command in instructions) {
			when (command.first) {
				'F' -> shipPosition = forward(orientation, command.second)
				'R', 'L' -> orientation = turn(orientation, command.first, command.second)
				'N' -> shipPosition = forward(Direction.NORTH, command.second)
				'S' -> shipPosition = forward(Direction.SOUTH, command.second)
				'W' -> shipPosition = forward(Direction.WEST, command.second)
				'E' -> shipPosition = forward(Direction.EAST, command.second)
				else -> {}
			}
		}
		return shipPosition
	}

	private fun forward(orientation: Direction, amount: Int): Pair<Int, Int> {
		return when (orientation) {
			Direction.NORTH -> shipPosition.first to shipPosition.second - amount
			Direction.SOUTH -> shipPosition.first to shipPosition.second + amount
			Direction.WEST -> shipPosition.first - amount to shipPosition.second
			Direction.EAST -> shipPosition.first + amount to shipPosition.second
		}
	}

	private fun turn(originalOrientation: Direction, turnDirection: Char, amount: Int): Direction {
		val offset = amount / 90 * (if (turnDirection == 'R') 1 else -1)
		return Direction.values()[Math.floorMod(originalOrientation.ordinal + offset, 4)]
	}

	fun navWithWaypoint(instructions: List<Pair<Char, Int>>): Pair<Int, Int> {
		for (command in instructions) {
			when (command.first) {
				'F' -> goToWaypoint(command.second)
				'R', 'L' -> rotateWaypoint(command.first, command.second)
				'N' -> moveWaypoint(Direction.NORTH, command.second)
				'S' -> moveWaypoint(Direction.SOUTH, command.second)
				'W' -> moveWaypoint(Direction.WEST, command.second)
				'E' -> moveWaypoint(Direction.EAST, command.second)
			}
		}
		return shipPosition
	}

	private fun moveWaypoint(direction: Direction, amount: Int) {
		waypoint = when (direction) {
			Direction.NORTH -> waypoint.first to waypoint.second - amount
			Direction.SOUTH -> waypoint.first to waypoint.second + amount
			Direction.WEST -> waypoint.first - amount to waypoint.second
			Direction.EAST -> waypoint.first + amount to waypoint.second
		}
	}

	private fun goToWaypoint(times: Int) {
		val offsetX = waypoint.first - shipPosition.first
		val offsetY = waypoint.second - shipPosition.second
		shipPosition = (shipPosition.first + offsetX * times) to (shipPosition.second + offsetY * times)
		waypoint = (shipPosition.first + offsetX) to (shipPosition.second + offsetY)
	}

	private fun rotateWaypoint(turnDirection: Char, amount: Int) {
		val offset = amount / 90
		val sign = if (turnDirection == 'R') 1 else -1
		(1..offset).forEach { _ -> rotateWaypoint(sign) }
	}

	private fun rotateWaypoint(sign: Int) {
		val offsetX = waypoint.first - shipPosition.first
		val offsetY = waypoint.second - shipPosition.second
		val newX = -1 * sign * offsetY
		val newY = sign * offsetX
		waypoint = shipPosition.first + newX to shipPosition.second + newY
	}
}

enum class Direction {
	NORTH,
	EAST,
	SOUTH,
	WEST
}
