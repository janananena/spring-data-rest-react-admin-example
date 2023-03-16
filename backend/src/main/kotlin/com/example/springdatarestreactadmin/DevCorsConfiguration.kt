package com.example.springdatarestreactadmin

import org.springframework.context.annotation.Profile
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.stereotype.Component
import org.springframework.web.servlet.config.annotation.CorsRegistry

@Component
@Profile("dev")
class DevCorsConfiguration : RepositoryRestConfigurer {
    val logger by logger()
    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration, cors: CorsRegistry) {
        logger.info("CORS Config: allowing cross-origin")
        cors.addMapping("/**")
            .allowedOrigins("http://localhost:5173")
            .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
            .allowCredentials(true)
            .allowedHeaders("*")
            .maxAge(3600)
    }

}