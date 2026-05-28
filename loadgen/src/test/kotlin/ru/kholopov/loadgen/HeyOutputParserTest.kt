package ru.kholopov.loadgen

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class HeyOutputParserTest {

	@Test
	fun `parses summary`() {
		val output = """
			Summary:
			  Average:	0.0442 secs
			  Requests/sec:	43.0253
			Latency distribution:
			  50% in 0.0699 secs
		""".trimIndent()

		val result = HeyOutputParser.parse(output)
		assertEquals(43.0253, result.requestsPerSecond, 0.0001)
		assertEquals(44.2, result.averageLatencyMs, 0.1)
		assertEquals(69.9, result.medianLatencyMs!!, 0.1)
	}
}
