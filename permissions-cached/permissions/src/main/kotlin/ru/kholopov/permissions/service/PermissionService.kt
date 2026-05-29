package ru.kholopov.permissions.service

import org.springframework.stereotype.Service
import ru.kholopov.permissions.dto.PermissionEntry
import ru.kholopov.permissions.repository.PermissionRepository

@Service
class PermissionService(
	private val permissionRepository: PermissionRepository,
) {

	fun getPermissions(username: String): List<PermissionEntry> =
		permissionRepository.findPermissionsByUsername(username)
			.map { PermissionEntry(method = it.method, url = it.url) }
}
