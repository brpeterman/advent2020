package io.github.brpeterman.advent2020

class Computer {
	var pointer: Int = 0
	var accumulator: Int = 0
	val instructions = ArrayList<Instruction>()

	constructor(program: String) {
		parseInput(program)
	}

	constructor(program: List<Instruction>) {
		instructions.addAll(program)
	}

	private fun parseInput(input: String) {
		input.split("\n")
				.filter { it.isNotEmpty() }
				.map { line ->
					val (code, valueString) = line.split(" ")
					instructions.add(Instruction(code, valueString.toInt()))
				}
	}

	fun execute(stopCondition: (Computer) -> Boolean) {
		while (!stopCondition.invoke(this)) {
			val instruction = instructions[pointer]
			when (instruction.code) {
				"acc" -> {
					accumulator += instruction.value
					pointer++
				}
				"jmp" -> pointer += instruction.value
				else -> pointer++
			}
		}
	}
}

class Instruction(val code: String, val value: Int)

fun buildNoRepeatCondition(): (Computer) -> Boolean {
	val visited = HashSet<Int>()
	return { state: Computer ->
		if (visited.contains(state.pointer) || state.pointer == state.instructions.size) {
			true // Stop
		} else {
			visited.add(state.pointer)
			false
		}
	}
}

fun findCorrectedOutput(input: String): Int {
	val badComputer = Computer(input)
	val originalInstructions = badComputer.instructions
	var pointer = 0
	while (pointer < originalInstructions.size) {
		val originalInstruction = originalInstructions[pointer]
		var newInstructionCode = "nop"
		if (originalInstruction.code == "nop") {
			newInstructionCode = "jmp"
		} else if (originalInstruction.code != "jmp") {
			pointer++
			continue
		}
		val newInstruction = Instruction(newInstructionCode, originalInstruction.value)
		val newInstructionSet = originalInstructions.clone() as ArrayList<Instruction>
		newInstructionSet[pointer] = newInstruction
		val computer = Computer(newInstructionSet)
		computer.execute(buildNoRepeatCondition())
		if (computer.pointer == originalInstructions.size) {
			return computer.accumulator
		}
		pointer++
	}
	throw IllegalStateException("Failed to find corruption")
}
