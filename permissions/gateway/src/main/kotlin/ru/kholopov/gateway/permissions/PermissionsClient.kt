package ru.kholopov.gateway.permissions

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestClientResponseException

@Component
class PermissionsClient(
	@Value("\${permissions.service.base-url:http://localhost:8082}") baseUrl: String,
	restClientBuilder: RestClient.Builder,
) {

	private val restClient: RestClient = restClientBuilder
		.baseUrl(baseUrl)
		.build()

	fun hasAccess(username: String, method: String, url: String): Boolean =
		try {
			restClient.post()
				.uri("/access/check")
				.header("X-Username", username)
				.contentType(MediaType.APPLICATION_JSON)
				.body(AccessCheckRequest(method = method, url = url))
				.retrieve()
				.body(AccessCheckResponse::class.java)
				?.allowed == true
		} catch (_: RestClientResponseException) {
			false
		}
}
