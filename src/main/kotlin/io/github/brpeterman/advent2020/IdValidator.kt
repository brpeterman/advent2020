package io.github.brpeterman.advent2020

class IdValidator {
	fun parseInput(input: String): List<Id> {
		return input.split("\n\n")
				.filter { it.isNotEmpty() }
				.map { constructId(it) }
	}

	fun countValid(ids: List<Id>): Int {
		return ids.filter { it.isValid() }
				.count()
	}

	private fun constructId(input: String): Id {
		var birthYear: Int? = null
		var issueYear: Int? = null
		var expirationYear: Int? = null
		var height: String? = null
		var hairColor: String? = null
		var eyeColor: String? = null
		var passportId: String? = null
		var countryId: String? = null
		input.split("""\s+""".toRegex())
				.filter { it.isNotEmpty() }
				.forEach { fieldSpec ->
					val (fieldName, value) = fieldSpec.split(":")
					when (fieldName) {
						"byr" -> birthYear = value.toInt()
						"iyr" -> issueYear = value.toInt()
						"eyr" -> expirationYear = value.toInt()
						"hgt" -> height = value
						"hcl" -> hairColor = value
						"ecl" -> eyeColor = value
						"pid" -> passportId = value
						"cid" -> countryId = value
					}
				}
		return Id(birthYear, issueYear, expirationYear, height, hairColor, eyeColor, passportId, countryId)
	}
}

class Id(
		val birthYear: Int?,
		val issueYear: Int?,
		val expirationYear: Int?,
		val height: String?,
		val hairColor: String?,
		val eyeColor: String?,
		val passportId: String?,
		val countryId: String?
) {
	fun isValid(): Boolean {
		return isValidYear(birthYear, 1920, 2002) &&
				isValidYear(issueYear, 2010, 2020) &&
				isValidYear(expirationYear, 2020, 2030) &&
				isValidHeight(height) &&
				isValidHairColor(hairColor) &&
				isValidEyeColor(eyeColor) &&
				isValidPassportId(passportId)
	}

	fun isValidYear(year: Int?, startInclusive: Int, endInclusive: Int): Boolean {
		return year != null &&
				(startInclusive..endInclusive).contains(year)
	}

	fun isValidHeight(height: String?): Boolean {
		if (height == null) {
			return false
		}

		val match = """\A(\d{2,3})(in|cm)\Z""".toRegex().find(height)
		if (match == null) {
			return false
		}

		val (magnitudeString, unit) = match.destructured
		val magnitude = magnitudeString.toInt()
		when (unit) {
			"cm" -> return (150..193).contains(magnitude)
			"in" -> return (59..76).contains(magnitude)
			else -> return false
		}
	}

	fun isValidHairColor(hairColor: String?): Boolean {
		return hairColor != null &&
				hairColor.matches("""\A#[a-z0-9]{6}\Z""".toRegex())
	}

	fun isValidEyeColor(eyeColor: String?): Boolean {
		return eyeColor != null &&
				setOf("amb", "blu", "brn", "grn", "gry", "hzl", "oth").contains(eyeColor)
	}

	fun isValidPassportId(passportId: String?): Boolean {
		return passportId != null &&
				passportId.matches("""\A\d{9}\Z""".toRegex())
	}
}
