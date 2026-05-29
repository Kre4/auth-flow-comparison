package ru.kholopov.permissions.dto

data class UserPermissionsResponse(
	val permissions: List<PermissionEntry>,
)
