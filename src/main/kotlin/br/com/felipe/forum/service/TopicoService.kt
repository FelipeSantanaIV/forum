package br.com.felipe.forum.service

import br.com.felipe.forum.dto.AtualizacaoTopicoForm
import br.com.felipe.forum.dto.TopicoForm
import br.com.felipe.forum.dto.TopicoPorCategoriaDto
import br.com.felipe.forum.dto.TopicoView
import br.com.felipe.forum.exception.NotFoundException
import br.com.felipe.forum.mapper.TopicoFormMapper
import br.com.felipe.forum.mapper.TopicoViewMapper
import br.com.felipe.forum.model.Topico
import br.com.felipe.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TopicoService(
    private var repository: TopicoRepository,
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper,
    private val notFoundMessage: String = "Topico não encontrado"
    ) {


    fun listar(
        nomeCurso: String?,
        paginacao: Pageable): Page<TopicoView> {
        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }
        return topicos.map {
            t -> topicoViewMapper.map(t) }
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicoViewMapper.map(topico)
    }

    fun obterTopico(id: Long): Topico {
        return repository.findById(id).stream()
            .filter { t ->
                t.id == id
            }.findFirst().get()
    }

    fun cadastrar(form: TopicoForm): TopicoView {
        val topico = topicoFormMapper.map(form)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun atualizar(form: AtualizacaoTopicoForm): TopicoView {
        val topico = repository.findById(form.id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        topico.titulo = form.titulo
        topico.mensagem = form.mensagem
        topico.dataAlteracao = LocalDate.now()
        return topicoViewMapper.map(topico)
    }

    fun deletar(id: Long) {
        repository.deleteById(id)
    }

    fun relatorio(): List<TopicoPorCategoriaDto> {
        return repository.relatorio()
    }
}