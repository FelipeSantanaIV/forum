package br.com.felipe.forum.service

import br.com.felipe.forum.model.Curso
import br.com.felipe.forum.repository.CursoRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
class CursoServiceTest {

    // Crie um mock do repositório
    private val repository = mockk<CursoRepository>()
    private val cursoService = CursoService(repository)

    @Test
    fun `deve buscar curso por id`() {
        // Mock do curso retornado pelo repositório
        val cursoEsperado = Curso(id = 1L, nome = "Kotlin Avançado", "Kotlin")

        // Configuração do comportamento do mock
        every { repository.getOne(1L) } returns cursoEsperado

        // Chama o método a ser testado
        val curso = cursoService.buscarPorId(1L)

        // Verifica se o método foi chamado corretamente
        verify(exactly = 1) { repository.getOne(1L) }

        // Asserção usando AssertJ
        assertThat(curso).isNotNull
        assertThat(curso.id).isEqualTo(1L)
        assertThat(curso.nome).isEqualTo("Kotlin Avançado")
    }
}