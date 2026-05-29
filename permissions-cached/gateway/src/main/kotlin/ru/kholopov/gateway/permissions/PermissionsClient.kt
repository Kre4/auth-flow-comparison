package ru.kholopov.gateway.permissions

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException

@Component
class PermissionsClient(
	@Value("\${permissions.service.base-url:http://localhost:8082}") baseUrl: String,
	restClientBuilder: RestClient.Builder,
	private val permissionsCache: PermissionsCache,
) {
	private val logger = LoggerFactory.getLogger(PermissionsClient::class.java)

	private val restClient: RestClient = restClientBuilder
		.baseUrl(baseUrl)
		.build()

	fun hasAccess(username: String, method: String, url: String): Boolean {
		val permissions = getPermissions(username)
		return permissions.any {
			it.method.equals(method, ignoreCase = true) && it.url == url
		}
	}

	private fun getPermissions(username: String): List<PermissionEntry> {
		permissionsCache.get(username)?.let { return it }
		logger.info("Using http request for fetching permissions")
		val permissions = fetchPermissions(username)
		permissionsCache.put(username, permissions)
		return permissions
	}

	private fun fetchPermissions(username: String): List<PermissionEntry> =
		try {
			restClient.get()
				.uri("/access/permissions")
				.header("X-Username", username)
				.retrieve()
				.body(UserPermissionsResponse::class.java)
				?.permissions
				.orEmpty()
		} catch (_: RestClientResponseException) {
			emptyList()
		}
}
