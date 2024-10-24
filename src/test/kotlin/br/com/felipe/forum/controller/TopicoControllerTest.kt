package br.com.felipe.forum.controller

import br.com.felipe.forum.config.JWTUtil
import br.com.felipe.forum.configuration.DatabaseContainerConfiguration
import br.com.felipe.forum.model.Role
import br.com.felipe.forum.model.UsuarioTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicoControllerTest: DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val RECURSO = "/topicos"
        private const val RECURSO_ID = "$RECURSO/%s"
    }

    @BeforeEach
    fun setup(){
        token = gerarToken()

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()).build()
    }

    @Test
    fun `deve retornar código 400 quando chamar topicos sem token` () {
        mockMvc.get(RECURSO).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `deve retornar o código 200 quando chamar topicos com token`() {
        mockMvc.get(RECURSO) {
            headers { token?.let { setBearerAuth(it) } }
        }.andExpect { status { isOk() } }


    }

    @Test
    fun `deve retornar codigo 200 quando chamar tópico por id com token`() {
        mockMvc.get(RECURSO_ID.format("2")) {
            headers { token?.let { setBearerAuth(it) } }
        }.andExpect { status { isOk() } }
    }

    private fun gerarToken(): String?{
        val authorities = mutableListOf(Role(1, "LEITURA_E_ESCRITA"))
        val usuario = UsuarioTest.buildToToken()
        return jwtUtil.generateToken(usuario.email, authorities)
    }
}