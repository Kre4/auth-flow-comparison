package ru.kholopov.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter
import org.springframework.security.web.SecurityFilterChain
import ru.kholopov.gateway.filter.PermissionAuthorizationFilter

@Configuration
@EnableWebSecurity
class SecurityConfig {

	@Bean
	fun securityFilterChain(
		http: HttpSecurity,
		permissionAuthorizationFilter: PermissionAuthorizationFilter,
	): SecurityFilterChain =
		http
			.authorizeHttpRequests { auth -> auth.anyRequest().authenticated() }
			.oauth2ResourceServer { oauth2 ->
				oauth2.jwt { jwt ->
					jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
				}
			}
			.addFilterAfter(permissionAuthorizationFilter, BearerTokenAuthenticationFilter::class.java)
			.csrf { it.disable() }
			.build()

	private fun jwtAuthenticationConverter(): JwtAuthenticationConverter =
		JwtAuthenticationConverter().apply {
			setPrincipalClaimName("sub")
			setJwtGrantedAuthoritiesConverter { emptyList() }
		}
}
