package ru.kholopov.echo_preauthorized

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EchoPreauthorizedApplication

fun main(args: Array<String>) {
	runApplication<EchoPreauthorizedApplication>(*args)
}
