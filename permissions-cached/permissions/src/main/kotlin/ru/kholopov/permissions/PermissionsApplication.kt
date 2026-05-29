package ru.kholopov.permissions

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PermissionsApplication

fun main(args: Array<String>) {
	runApplication<PermissionsApplication>(*args)
}
