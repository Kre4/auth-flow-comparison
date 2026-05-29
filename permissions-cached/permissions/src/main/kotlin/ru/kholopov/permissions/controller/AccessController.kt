package ru.kholopov.permissions.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.kholopov.permissions.dto.UserPermissionsResponse
import ru.kholopov.permissions.service.PermissionService

@RestController
@RequestMapping("/access")
class AccessController(
	private val permissionService: PermissionService,
) {

	@GetMapping("/permissions")
	fun getPermissions(
		@RequestHeader("X-Username") username: String,
	): UserPermissionsResponse =
		UserPermissionsResponse(
			permissions = permissionService.getPermissions(username),
		)
}
