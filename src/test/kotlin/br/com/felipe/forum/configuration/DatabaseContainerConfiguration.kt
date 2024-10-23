package br.com.felipe.forum.configuration

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container

@SpringJUnitConfig
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class DatabaseContainerConfiguration {

    companion object {
        @Container
        private val mySqlContainer = MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testedb")
            withUsername("felipe")
            withPassword("123456")
            withReuse(true)
        }

        @Container
        private val redisContainer = GenericContainer<Nothing>("redis:latest").apply {
            withExposedPorts(6379)
            withReuse(true)
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry){
            registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mySqlContainer::getPassword)
            registry.add("spring.datasource.username", mySqlContainer::getUsername)

            registry.add("spring.redis.host", redisContainer::getHost)
            registry.add("spring.redis.port", redisContainer::getExposedPorts)
        }
    }
}