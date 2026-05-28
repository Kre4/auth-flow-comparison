package ru.kholopov.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer

@Configuration
class TokenCustomizerConfig(
	private val userRolesQuery: UserRolesQuery,
) {

	@Bean
	fun accessTokenCustomizer(): OAuth2TokenCustomizer<JwtEncodingContext> =
		OAuth2TokenCustomizer { context ->
			if (OAuth2TokenType.ACCESS_TOKEN != context.tokenType) {
				return@OAuth2TokenCustomizer
			}

			val username = context.getPrincipal<Authentication>().name
			val roleIds = userRolesQuery.findRoleIds(username)

			context.claims.claims { claims ->
				val exp = claims["exp"]
				claims.clear()
				claims["exp"] = exp
				claims["sub"] = username
				claims["roleId"] = roleIds
			}
		}
}
