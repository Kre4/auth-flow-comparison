package ru.kholopov.gateway.permissions

data class UserPermissionsResponse(
	val permissions: List<PermissionEntry>,
)
