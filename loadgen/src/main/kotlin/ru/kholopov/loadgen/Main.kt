package ru.kholopov.loadgen

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.StandardCopyOption
import kotlin.system.exitProcess

private const val REQUESTS = 1000
private const val CONCURRENCY = 50

fun main(args: Array<String>) {
	if (args.size != 2) {
		printUsage()
		exitProcess(1)
	}

	val scenario = Scenario.parse(args[0])
	if (scenario == Scenario.CACHE) {
		println("Сценарий cache пока не реализован.")
		exitProcess(0)
	}

	val token = args[1]
	val hey = resolveHeyExe()
	val command = buildHeyCommand(hey, scenario.url, token)

	println("Команда hey:")
	println(command.powershell)
	println()

	val output = runHey(command)
	printResults(scenario, output)
}

private fun resolveHeyExe(): Path {
	val resource = Thread.currentThread().contextClassLoader.getResource("hey/hey.exe")
		?: error("hey.exe не найден в resources/hey/hey.exe")

	if (resource.protocol == "file") {
		return Path.of(resource.toURI())
	}

	val temp = Files.createTempFile("hey-", ".exe")
	temp.toFile().deleteOnExit()
	resource.openStream().use { input ->
		Files.copy(input, temp, StandardCopyOption.REPLACE_EXISTING)
	}
	return temp
}

private data class HeyCommand(
	val argv: List<String>,
	val powershell: String,
)

private fun buildHeyCommand(hey: Path, url: String, token: String): HeyCommand {
	val heyPath = hey.toAbsolutePath().toString()
	val header = "Authorization: Bearer $token"
	val argv = listOf(
		heyPath,
		"-n", REQUESTS.toString(),
		"-c", CONCURRENCY.toString(),
		"-m", "GET",
		"-H", header,
		url,
	)
	val argsTail = "-n $REQUESTS -c $CONCURRENCY -m GET -H ${quote(header)} ${quote(url)}"
	return HeyCommand(
		argv = argv,
		powershell = "& ${quote(heyPath)} $argsTail",
	)
}

private fun quote(value: String): String =
	"\"${value.replace("\"", "\\\"")}\""

private fun runHey(command: HeyCommand): String {
	val process = ProcessBuilder(command.argv)
		.redirectErrorStream(true)
		.start()

	val output = process.inputStream.bufferedReader().readText()
	val exitCode = process.waitFor()
	if (exitCode != 0) {
		error("hey завершился с кодом $exitCode\n$output")
	}
	return output
}

private fun printResults(scenario: Scenario, heyOutput: String) {
	val metrics = HeyOutputParser.parse(heyOutput)
	println()
	println("=== ${scenario.name} ===")
	println("URL: ${scenario.url}")
	println("Throughput: ${"%.2f".format(metrics.requestsPerSecond)} req/s")
	println("Latency (avg): ${"%.2f".format(metrics.averageLatencyMs)} ms")
	metrics.medianLatencyMs?.let { println("Latency (p50): ${"%.2f".format(it)} ms") }
	println()
	println(heyOutput)
}

private fun printUsage() {
	println("Usage: loadgen <jwt|permission|cache> <access_token>")
}
