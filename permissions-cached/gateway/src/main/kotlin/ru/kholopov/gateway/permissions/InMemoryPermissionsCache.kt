package ru.kholopov.gateway.permissions

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
@ConditionalOnProperty(name = ["permissions.cache.redis.enabled"], havingValue = "false")
class InMemoryPermissionsCache : PermissionsCache {

	private val cache = ConcurrentHashMap<String, List<PermissionEntry>>()

	override fun get(username: String): List<PermissionEntry>? = cache[username]

	override fun put(username: String, permissions: List<PermissionEntry>) {
		cache[username] = permissions
	}
}
