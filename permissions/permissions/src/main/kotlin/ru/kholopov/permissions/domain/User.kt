package ru.kholopov.permissions.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table("users")
data class User(
	@Id val username: String,
	val password: String,
	val enabled: Boolean,
)
