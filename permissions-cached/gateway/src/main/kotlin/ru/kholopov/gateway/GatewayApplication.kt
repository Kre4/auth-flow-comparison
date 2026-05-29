package ru.kholopov.gateway

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import ru.kholopov.gateway.permissions.PermissionsCacheProperties

@SpringBootApplication
@EnableConfigurationProperties(PermissionsCacheProperties::class)
class GatewayApplication

fun main(args: Array<String>) {
	runApplication<GatewayApplication>(*args)
}
