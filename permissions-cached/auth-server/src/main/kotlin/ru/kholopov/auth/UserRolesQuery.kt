package ru.kholopov.auth

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class UserRolesQuery(
	private val jdbcTemplate: JdbcTemplate,
) {

	fun findRoleIds(username: String): List<Int> =
		jdbcTemplate.queryForList(
			"select role_id from user_roles where username = ?",
			Int::class.java,
			username,
		)
}
