package unq.tpi.desapp.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/")
class CompraEnCasaController {

    @GetMapping("/")
    fun default(): ResponseEntity<String?>? {
        return ResponseEntity("API de compras en casa", HttpStatus.OK)
    }
}