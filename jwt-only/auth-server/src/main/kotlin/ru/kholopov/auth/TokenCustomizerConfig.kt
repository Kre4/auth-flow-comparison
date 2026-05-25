package ru.kholopov.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer

@Configuration
class TokenCustomizerConfig {

	@Bean
	fun jwtTokenCustomizer(): OAuth2TokenCustomizer<JwtEncodingContext> =
		OAuth2TokenCustomizer { context ->
			if (OAuth2TokenType.ACCESS_TOKEN != context.tokenType) {
				return@OAuth2TokenCustomizer
			}
			val authorities = context.getPrincipal<Authentication>().authorities
				.map(GrantedAuthority::getAuthority)
				.toSet()
			if (authorities.isNotEmpty()) {
				context.claims.claim("authorities", authorities)
			}
		}
}
