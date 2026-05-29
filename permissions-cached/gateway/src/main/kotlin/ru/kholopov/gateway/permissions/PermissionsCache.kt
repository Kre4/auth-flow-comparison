package ru.kholopov.gateway.permissions

interface PermissionsCache {

	fun get(username: String): List<PermissionEntry>?

	fun put(username: String, permissions: List<PermissionEntry>)
}
