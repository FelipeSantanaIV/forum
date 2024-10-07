package br.com.felipe.forum.service

import br.com.felipe.forum.dto.TopicoForm
import br.com.felipe.forum.dto.TopicoView
import br.com.felipe.forum.mapper.TopicoFormMapper
import br.com.felipe.forum.mapper.TopicoViewMapper
import br.com.felipe.forum.model.Topico
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import kotlin.collections.ArrayList

@Service
class TopicoService(
    private var topicos: List<Topico> = ArrayList(),
    private val topicoViewMapper: TopicoViewMapper,
    private val topicoFormMapper: TopicoFormMapper
    ) {

    fun listar(): List<TopicoView> {
        return topicos.stream().map {
            t -> topicoViewMapper.map(t) }.collect(Collectors.toList())
    }

    fun buscarPorId(id: Long): TopicoView {
        val topico = topicos.stream().filter { t ->
            t.id == id
        }.findFirst().get()
        return topicoViewMapper.map(topico)
    }

    fun obterTopico(id: Long): Topico {
        return topicos.stream()
            .filter { t ->
                t.id == id
            }.findFirst().get()
    }

    fun cadastrar(form: TopicoForm) {
        val topico = topicoFormMapper.map(form)
        topico.id = topicos.size.toLong() + 1
        topicos = topicos.plus(topico)
    }
}