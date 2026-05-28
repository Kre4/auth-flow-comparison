package ru.kholopov.permissions.dto

data class AccessCheckRequest(
	val method: String,
	val url: String,
)
