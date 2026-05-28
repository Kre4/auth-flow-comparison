package ru.kholopov.permissions.service

import org.springframework.stereotype.Service
import ru.kholopov.permissions.repository.PermissionRepository

@Service
class PermissionService(
	private val permissionRepository: PermissionRepository,
) {

	fun hasAccess(username: String, method: String, url: String): Boolean =
		permissionRepository.hasAccess(username, method.uppercase(), url)
}
