package ru.kholopov.loadgen

enum class Scenario(val url: String) {
	JWT("http://localhost:8081/echo?value=load1"),
	PERMISSION("http://localhost:8083/echo/echo?value=load2"),
	CACHE("http://localhost:8083/echo/echo?value=load3"),
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
