package ru.kholopov.permissions.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import tools.jackson.databind.ObjectMapper

@SpringBootTest
@AutoConfigureMockMvc
class AccessControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@Test
	fun `alice has GET access to echo`() {
		mockMvc.post("/access/check") {
			header("X-Username", "alice")
			contentType = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(mapOf("method" to "GET", "url" to "/echo"))
		}.andExpect {
			status { isOk() }
			content { json("""{"allowed":true}""") }
		}
	}

	@Test
	fun `bob has no POST access to echo`() {
		mockMvc.post("/access/check") {
			header("X-Username", "bob")
			contentType = MediaType.APPLICATION_JSON
			content = objectMapper.writeValueAsString(mapOf("method" to "POST", "url" to "/echo"))
		}.andExpect {
			status { isOk() }
			content { json("""{"allowed":false}""") }
		}
	}
}
