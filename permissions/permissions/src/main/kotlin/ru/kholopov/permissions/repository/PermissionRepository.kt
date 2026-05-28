package ru.kholopov.permissions.repository

import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.Repository
import ru.kholopov.permissions.domain.User

interface PermissionRepository : Repository<User, String> {

	@Query(
		"""
		SELECT EXISTS (
			SELECT 1
			FROM user_roles ur
			INNER JOIN role_urls ru ON ru.role_id = ur.role_id
			INNER JOIN urls u ON u.id = ru.url_id
			WHERE ur.username = :username
			  AND u.method = :method
			  AND u.url = :url
		)
		""",
	)
	fun hasAccess(username: String, method: String, url: String): Boolean
}
