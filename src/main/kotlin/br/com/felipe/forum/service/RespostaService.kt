package br.com.felipe.forum.service

import br.com.felipe.forum.dto.RespostaForm
import br.com.felipe.forum.mapper.RespostaFormMapper
import br.com.felipe.forum.mapper.TopicoFormMapper
import br.com.felipe.forum.model.Resposta
import br.com.felipe.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class RespostaService (
    private var respostas: List<Resposta> = ArrayList(),
    private val respostaFormMapper: RespostaFormMapper,
    private val topicoService: TopicoService

) {

    fun cadastrar(form: RespostaForm, idTopico: Long) {
        val resposta = respostaFormMapper.map(form)
        resposta.id = respostas.size.toLong() + 1
        resposta.topico = topicoService.obterTopico(idTopico)
        respostas = respostas.plus(resposta)
    }
}
