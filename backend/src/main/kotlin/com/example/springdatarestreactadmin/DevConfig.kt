package com.example.springdatarestreactadmin

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("dev")
class DevConfig {
    val logger by logger()

    @Bean
    fun dbInitializer(
        testEntityRepository: TestEntityRepository
    ) = ApplicationRunner {
        logger.info(">> Initializing dev database")
        if (!testEntityRepository.findAll().any()) {
            testEntityRepository.save(TestEntity(1,"test1"))
            testEntityRepository.save(TestEntity(2,"test2"))
        }
    }
}