package ru.kholopov.gateway.filter

import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import ru.kholopov.gateway.permissions.PermissionsClient

@SpringBootTest
@AutoConfigureMockMvc
class PermissionAuthorizationFilterTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@MockitoBean
	private lateinit var permissionsClient: PermissionsClient

	@Test
	@WithMockUser("alice")
	fun `returns 403 when permissions service denies access`() {
		`when`(permissionsClient.hasAccess("alice", "GET", "/echo")).thenReturn(false)

		mockMvc.get("/echo") {
			accept = MediaType.APPLICATION_JSON
		}.andExpect {
			status { isForbidden() }
		}
	}

	@Test
	@WithMockUser("alice")
	fun `continues when permissions service allows access`() {
		`when`(permissionsClient.hasAccess("alice", "GET", "/echo")).thenReturn(true)

		val status = mockMvc.get("/echo") {
			accept = MediaType.APPLICATION_JSON
		}.andReturn().response.status

		assertNotEquals(401, status)
		assertNotEquals(403, status)
	}
}
