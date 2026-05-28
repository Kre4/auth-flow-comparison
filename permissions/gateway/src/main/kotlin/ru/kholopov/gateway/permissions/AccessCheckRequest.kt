package ru.kholopov.gateway.permissions

data class AccessCheckRequest(
	val method: String,
	val url: String,
)
