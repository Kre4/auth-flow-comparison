package ru.kholopov.loadgen

data class HeyResult(
	val requestsPerSecond: Double,
	val averageLatencyMs: Double,
	val medianLatencyMs: Double?,
)

object HeyOutputParser {

	private val averageRegex = Regex("""^\s*Average:\s+([\d.]+)\s+secs""", RegexOption.MULTILINE)
	private val rpsRegex = Regex("""^\s*Requests/sec:\s+([\d.]+)""", RegexOption.MULTILINE)
	private val medianRegex = Regex("""^\s*50%+\s+in\s+([\d.]+)\s+secs""", RegexOption.MULTILINE)

	fun parse(output: String): HeyResult {
		fun seconds(regex: Regex): Double =
			regex.find(output)?.groupValues?.get(1)?.toDouble()
				?: error("Не удалось разобрать вывод hey")

		val averageSec = seconds(averageRegex)
		return HeyResult(
			requestsPerSecond = seconds(rpsRegex),
			averageLatencyMs = averageSec * 1000,
			medianLatencyMs = medianRegex.find(output)?.groupValues?.get(1)?.toDouble()?.times(1000),
		)
	}
}
