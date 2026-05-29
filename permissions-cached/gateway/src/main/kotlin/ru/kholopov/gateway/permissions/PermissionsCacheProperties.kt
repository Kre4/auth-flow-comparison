package ru.kholopov.gateway.permissions

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "permissions.cache")
data class PermissionsCacheProperties(
	val redis: Redis = Redis(),
	val keyPrefix: String = "user-permissions:",
	val ttlSeconds: Long = 300,
) {
	data class Redis(
		val enabled: Boolean = true,
	)
}
