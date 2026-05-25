package ru.kholopov.echo_preauthorized.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {

	@Bean
	fun securityFilterChain(http: HttpSecurity): SecurityFilterChain =
		http
			.authorizeHttpRequests { auth -> auth.anyRequest().authenticated() }
			.oauth2ResourceServer { oauth2 ->
				oauth2.jwt { jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()) }
			}
			.csrf { it.disable() }
			.build()

	@Bean
	fun jwtAuthenticationConverter(): Converter<Jwt, AbstractAuthenticationToken> {
		val scopeAuthorities = JwtGrantedAuthoritiesConverter().apply {
			setAuthoritiesClaimName("scope")
			setAuthorityPrefix("SCOPE_")
		}
		val roleAuthorities = JwtGrantedAuthoritiesConverter().apply {
			setAuthoritiesClaimName("authorities")
			setAuthorityPrefix("")
		}
		return JwtAuthenticationConverter().apply {
			setJwtGrantedAuthoritiesConverter { jwt ->
				buildList {
					addAll(scopeAuthorities.convert(jwt) ?: emptyList())
					addAll(roleAuthorities.convert(jwt) ?: emptyList())
				}
			}
		}
	}
}
