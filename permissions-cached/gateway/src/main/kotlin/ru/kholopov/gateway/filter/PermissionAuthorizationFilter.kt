package ru.kholopov.gateway.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import ru.kholopov.gateway.permissions.PermissionsClient

@Component
class PermissionAuthorizationFilter(
	private val permissionsClient: PermissionsClient,
) : OncePerRequestFilter() {

	override fun shouldNotFilter(request: HttpServletRequest): Boolean =
		!request.requestURI.startsWith("/echo")

	override fun doFilterInternal(
		request: HttpServletRequest,
		response: HttpServletResponse,
		filterChain: FilterChain,
	) {
		val authentication = SecurityContextHolder.getContext().authentication
		if (authentication == null || !authentication.isAuthenticated) {
			response.sendError(HttpStatus.UNAUTHORIZED.value())
			return
		}

		val path = request.requestURI.substringBefore('?')
		val allowed = permissionsClient.hasAccess(
			username = authentication.name,
			method = request.method,
			url = path,
		)

		if (!allowed) {
			response.sendError(HttpStatus.FORBIDDEN.value())
			return
		}

		filterChain.doFilter(request, response)
	}
}
