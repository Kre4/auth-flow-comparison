package ru.kholopov.echo_preauthorized.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/echo")
class EchoController {

    @GetMapping
    fun echo(@RequestParam value: String): String {
        return value
    }

    @GetMapping("/unauth")
    fun echo401(@RequestParam value: String): String {
        return value
    }
}
