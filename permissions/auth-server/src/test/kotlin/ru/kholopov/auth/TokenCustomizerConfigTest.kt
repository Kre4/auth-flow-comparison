package ru.kholopov.auth

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext
import java.time.Instant

class TokenCustomizerConfigTest {

	private val userRolesQuery: UserRolesQuery = mock()
	private val customizer = TokenCustomizerConfig(userRolesQuery).accessTokenCustomizer()

	@Test
	fun `access token contains only sub roleId and exp`() {
		`when`(userRolesQuery.findRoleIds("alice")).thenReturn(listOf(1, 2))

		val claimsBuilder = JwtClaimsSet.builder()
			.subject("ignored")
			.issuer("http://localhost")
			.audience(listOf("demo-client"))
			.claim("scope", listOf("api.read"))
			.issuedAt(Instant.now())
			.expiresAt(Instant.now().plusSeconds(3600))
			.id("jti-1")

		val context = JwtEncodingContext.with(JwsHeader.with(SignatureAlgorithm.RS256), claimsBuilder)
			.principal(UsernamePasswordAuthenticationToken("alice", null, emptyList()))
			.tokenType(OAuth2TokenType.ACCESS_TOKEN)
			.build()

		customizer.customize(context)

		val claims = context.claims.build().claims
		assertEquals(setOf("exp", "sub", "roleId"), claims.keys)
		assertEquals("alice", claims["sub"])
		assertEquals(listOf(1, 2), claims["roleId"])
		assertTrue(claims.containsKey("exp"))
	}
}
