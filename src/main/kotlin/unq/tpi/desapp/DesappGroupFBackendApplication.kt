package unq.tpi.desapp

import it.ozimov.springboot.mail.configuration.EnableEmailTools
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter
import java.util.*
import java.util.stream.Collectors


@SpringBootApplication
@EnableEmailTools
class DesappGroupFBackendApplication
fun main(args: Array<String>) {
    runApplication<DesappGroupFBackendApplication>(*args)


}
//@SpringBootApplication
//class SpringBootGraphqlApplication {
//    @Bean
//    fun corsFilter(): CorsFilter {
//        val source = UrlBasedCorsConfigurationSource()
//        val config = CorsConfiguration()
//        config.allowCredentials = true
//        config.allowedOrigins = Collections.singletonList("*")
//        config.allowedHeaders = Collections.singletonList("*")
//        config.allowedMethods = Arrays.stream(HttpMethod.values()).map({ obj: HttpMethod -> obj.name }).collect(Collectors.toList())
//        source.registerCorsConfiguration("/**", config)
//        return CorsFilter(source)
//    }
//
//    companion object {
//        @JvmStatic
//        fun main(args: Array<String>) {
//            SpringApplication.run(SpringBootGraphqlApplication::class.java, *args)
//        }
//    }
//}