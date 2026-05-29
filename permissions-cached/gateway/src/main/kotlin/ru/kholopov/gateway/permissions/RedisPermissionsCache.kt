package ru.kholopov.gateway.permissions

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import tools.jackson.core.type.TypeReference
import tools.jackson.databind.ObjectMapper
import java.time.Duration

@Component
@ConditionalOnProperty(name = ["permissions.cache.redis.enabled"], havingValue = "true", matchIfMissing = true)
class RedisPermissionsCache(
	private val redisTemplate: StringRedisTemplate,
	private val objectMapper: ObjectMapper,
	private val cacheProperties: PermissionsCacheProperties,
) : PermissionsCache {

	private val permissionsType = object : TypeReference<List<PermissionEntry>>() {}

	override fun get(username: String): List<PermissionEntry>? {
		val json = redisTemplate.opsForValue().get(cacheKey(username)) ?: return null
		return objectMapper.readValue(json, permissionsType)
	}

	override fun put(username: String, permissions: List<PermissionEntry>) {
		val json = objectMapper.writeValueAsString(permissions)
		redisTemplate.opsForValue().set(
			cacheKey(username),
			json,
			Duration.ofSeconds(cacheProperties.ttlSeconds),
		)
	}

	private fun cacheKey(username: String): String = "${cacheProperties.keyPrefix}$username"
}
