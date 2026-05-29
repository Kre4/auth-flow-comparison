package ru.kholopov.auth

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.JdbcUserDetailsManager
import javax.sql.DataSource

@Configuration
class UserDetailsConfig {

    @Bean
    fun userDetailsService(dataSource: DataSource): UserDetailsService {
        val users = JdbcUserDetailsManager(dataSource)
        users.setUsersByUsernameQuery(
            "select username, password, enabled from users where username = ?",
        )
        users.setAuthoritiesByUsernameQuery(
            """
			select username, 'none' from users where username = ?
			""".trimIndent(),
        )
        return users
    }
}
