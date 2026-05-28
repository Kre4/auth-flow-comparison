package ru.kholopov.loadgen

enum class Scenario(val url: String) {
	JWT("http://localhost:8081/echo?value=load"),
	PERMISSION("http://localhost:8083/echo?value=load"),
	CACHE(""),
	;

	companion object {
		fun parse(arg: String): Scenario =
			when (arg.lowercase()) {
				"jwt" -> JWT
				"permission" -> PERMISSION
				"cache" -> CACHE
				else -> error("Usage: loadgen <jwt|permission|cache>")
			}
	}
}
