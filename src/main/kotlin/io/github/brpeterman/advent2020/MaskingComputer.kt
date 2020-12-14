package io.github.brpeterman.advent2020

class MaskingComputer(input: String, version: Int = 1) {
	companion object {
		private val MEMORY_SIZE = 36
	}

	val memory = HashMap<Long, Long>()

	init {
		var bitmask = HashMap<Int, Char>()
		val lines = input.split("\n")
				.filter { it.isNotEmpty() }
		for (line in lines) {
			val pattern = """\Amem\[(\d+)] = (\d+)\Z""".toRegex()
			val match = pattern.find(line)
			if (match == null) { // Mask definition line
				val (_, mask) = line.split(" = ")
				bitmask = HashMap()
				mask.toCharArray().forEachIndexed { index, char ->
					when (char) {
						'0', '1' -> bitmask[MEMORY_SIZE - index - 1] = char
					}
				}
			} else { // Set memory line
				val (address, value) = match.destructured
				if (version == 1) {
					setMemory(address.toLong(), value.toLong(), bitmask)
				} else if (version == 2) {
					setMultiple(address.toLong(), value.toLong(), bitmask)
				}
			}
		}
	}

	private fun setMemory(address: Long, value: Long, bitmask: Map<Int, Char>) {
		var output = value
		bitmask.forEach { position, bit ->
			output = when (bit) {
				'1' -> output or (1L shl position)
				'0' -> output and (1L shl position).inv()
				else -> throw IllegalStateException("Invalid bitmask value: ${bit}")
			}
		}
		memory[address] = output
	}

	private fun setMultiple(address: Long, value: Long, bitmask: Map<Int, Char>) {
		var moddedAddress = address
		bitmask.forEach { position, bit ->
			moddedAddress = when(bit) {
				'1' -> moddedAddress or (1L shl position)
				else -> moddedAddress
			}
		}
		val addresses = setOf(moddedAddress).toMutableSet()
		for (index in (0..35)) {
			if (bitmask[index] == null) {
				for (floatBit in (0..1)) {
					val newAddresses = HashSet<Long>()
					for (addr in addresses) {
						newAddresses.add(when (floatBit) {
							1 -> addr or (1L shl index)
							0 -> addr and (1L shl index).inv()
							else -> addr
						})
					}
					addresses.addAll(newAddresses)
				}
			}
		}

		addresses.forEach { memory[it] = value }
	}
}
