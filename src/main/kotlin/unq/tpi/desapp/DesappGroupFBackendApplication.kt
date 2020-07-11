package unq.tpi.desapp

import it.ozimov.springboot.mail.configuration.EnableEmailTools
import org.springframework.boot.autoconfigure.SpringBootApplication

import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication
@EnableEmailTools
class DesappGroupFBackendApplication
fun main(args: Array<String>) {
    runApplication<DesappGroupFBackendApplication>(*args)
}