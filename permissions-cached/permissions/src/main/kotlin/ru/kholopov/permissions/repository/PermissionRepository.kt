package ru.kholopov.permissions.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.Repository
import ru.kholopov.permissions.domain.User

interface PermissionRepository : Repository<User, String> {

	@Query(
		"""
		SELECT DISTINCT u.method, u.url
		FROM user_roles ur
		INNER JOIN role_urls ru ON ru.role_id = ur.role_id
		INNER JOIN urls u ON u.id = ru.url_id
		WHERE ur.username = :username
		ORDER BY u.method, u.url
		""",
	)
	fun findPermissionsByUsername(username: String): List<PermissionRow>
}
