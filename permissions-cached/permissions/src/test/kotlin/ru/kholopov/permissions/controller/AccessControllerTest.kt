package ru.kholopov.permissions.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
class AccessControllerTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Test
	fun `alice has GET and POST access to echo`() {
		mockMvc.get("/access/permissions") {
			header("X-Username", "alice")
		}.andExpect {
			status { isOk() }
			content {
				json(
					"""
					{
						"permissions":[
							{"method":"GET","url":"/echo"},
							{"method":"POST","url":"/echo"}
						]
					}
					""".trimIndent(),
				)
			}
		}
	}

	@Test
	fun `bob has only GET access to echo`() {
		mockMvc.get("/access/permissions") {
			header("X-Username", "bob")
		}.andExpect {
			status { isOk() }
			content {
				json(
					"""
					{
						"permissions":[
							{"method":"GET","url":"/echo"}
						]
					}
					""".trimIndent(),
				)
			}
		}
	}
}
