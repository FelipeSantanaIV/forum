package br.com.felipe.forum.service

import br.com.felipe.forum.dto.AtualizacaoRespostaForm
import br.com.felipe.forum.dto.RespostaForm
import br.com.felipe.forum.dto.RespostaView
import br.com.felipe.forum.mapper.RespostaFormMapper
import br.com.felipe.forum.mapper.RespostaViewMapper
import br.com.felipe.forum.mapper.TopicoFormMapper
import br.com.felipe.forum.model.Resposta
import br.com.felipe.forum.model.Topico
import org.springframework.stereotype.Service

@Service
class RespostaService (
    private var respostas: List<Resposta> = ArrayList(),
    private val respostaFormMapper: RespostaFormMapper,
    private val respostaViewMapper: RespostaViewMapper,
    private val topicoService: TopicoService

) {

    fun cadastrar(form: RespostaForm, idTopico: Long) {
        val resposta = respostaFormMapper.map(form)
        resposta.id = respostas.size.toLong() + 1
        resposta.topico = topicoService.obterTopico(idTopico)
        respostas = respostas.plus(resposta)
    }

    fun atualizar(form: AtualizacaoRespostaForm): RespostaView {
        val resposta = respostas.stream().filter { r ->
            r.id == form.id
        }.findFirst().get()
        val respostaAtualizada = Resposta(
            id = form.id,
            mensagem = form.mensagem,
            autor = resposta.autor,
            topico = resposta.topico,
            solucao = form.solucao
        )
        respostas = respostas.minus(resposta).plus(respostaAtualizada)
        return respostaViewMapper.map(respostaAtualizada)
    }

    fun deletar(id: Long) {
        val resposta = respostas.stream().filter { r ->
            r.id == id
        }.findFirst().get()
        respostas = respostas.minus(resposta)
    }
}
