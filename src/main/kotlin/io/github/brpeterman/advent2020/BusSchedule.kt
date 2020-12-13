package io.github.brpeterman.advent2020

import kotlin.math.abs

class BusSchedule {
	fun earliestDeparture(arrivalTime: Long, buses: List<Int>): Long {
		val sortedBuses = buses.sortedBy { waitTime(arrivalTime, it) }
		return sortedBuses.first() * waitTime(arrivalTime, sortedBuses.first())
	}

	private fun waitTime(arrivalTime: Long, bus: Int): Long {
		return abs((arrivalTime % bus) - bus)
	}

	fun findMagicTimestamp(buses: List<Bus>): Long {
		val sortedBuses = buses.sortedByDescending { it.id }
		val product = sortedBuses.fold(1L, { acc, pair -> acc * pair.id })
		// Chinese remainder theorem solution. I don't really get it so I'm not particularly satisfied with it.
		return sortedBuses
				.map { bus ->
					val remainder = (bus.id - bus.offset) % bus.id
					val partialProduct = product / bus.id
					remainder * inverse(partialProduct, bus.id) * partialProduct
				}
				.sum() % product
	}

	fun inverse(num: Long, mod: Long): Long {
		var currentMod = mod
		var currentNum = num
		var x0 = 0L
		var x1 = 1L
		while (currentNum > 1) {
			val quotient = currentNum / currentMod
			val prevMod = currentMod
			currentMod = currentNum % currentMod
			currentNum = prevMod
			val swap = x0
			x0 = x1 - quotient * x0
			x1 = swap
		}
		return if (x1 < 0) x1 + mod else x1
	}
}

class Bus(val id: Long, val offset: Long)
