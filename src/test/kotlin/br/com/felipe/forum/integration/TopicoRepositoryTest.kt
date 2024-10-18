package br.com.felipe.forum.integration

import br.com.felipe.forum.dto.TopicoPorCategoriaDto
import br.com.felipe.forum.model.TopicoTest
import br.com.felipe.forum.repository.TopicoRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicoRepositoryTest {

    @Autowired
    private lateinit var topicoRepository: TopicoRepository

    private val topico = TopicoTest.build()

    companion object {
        @Container
        private val mySqlContainer = MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testedb")
            withUsername("felipe")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry){
            registry.add("spring.datasource.url", mySqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mySqlContainer::getPassword)
            registry.add("spring.datasource.username", mySqlContainer::getUsername)
        }
    }

    @Test
    fun `deve gerar um relatorio` () {
        topicoRepository.save(topico)
        val relatorio = topicoRepository.relatorio()

        assertThat(relatorio).isNotNull
        assertThat(relatorio.first()).isExactlyInstanceOf(TopicoPorCategoriaDto::class.java)

    }

    @Test
    fun `deve listar topico pelo nome do curso`(){
        topicoRepository.save(topico)
        val topico = topicoRepository.findByCursoNome(topico.curso.nome, PageRequest.of(0, 5))

        assertThat(topico).isNotNull
    }
}