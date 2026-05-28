package ru.kholopov.permissions.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kholopov.permissions.dto.AccessCheckRequest
import ru.kholopov.permissions.dto.AccessCheckResponse
import ru.kholopov.permissions.service.PermissionService

@RestController
@RequestMapping("/access")
class AccessController(
	private val permissionService: PermissionService,
) {

	@PostMapping("/check")
	fun check(
		@RequestHeader("X-Username") username: String,
		@RequestBody request: AccessCheckRequest,
	): AccessCheckResponse =
		AccessCheckResponse(
			allowed = permissionService.hasAccess(username, request.method, request.url),
		)
}
