package com.example.springdatarestreactadmin

import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry


@Configuration
class MyRepositoryRestConfiguration : RepositoryRestConfigurer {
    val logger by logger()

    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration, cors: CorsRegistry?) {
        logger.info("REST Config: allowing exposure of ids")
        config.exposeIdsFor(TestEntity::class.java)
    }
}