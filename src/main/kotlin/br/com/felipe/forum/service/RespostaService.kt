package br.com.felipe.forum.service

import br.com.felipe.forum.dto.AtualizacaoRespostaForm
import br.com.felipe.forum.dto.RespostaForm
import br.com.felipe.forum.dto.RespostaView
import br.com.felipe.forum.mapper.RespostaFormMapper
import br.com.felipe.forum.mapper.RespostaViewMapper
import br.com.felipe.forum.mapper.TopicoFormMapper
import br.com.felipe.forum.model.Resposta
import br.com.felipe.forum.model.Topico
import br.com.felipe.forum.repository.RespostaRepository
import org.springframework.stereotype.Service

@Service
class RespostaService (
    private var respostas: List<Resposta> = ArrayList(),
    private val respostaFormMapper: RespostaFormMapper,
    private val respostaViewMapper: RespostaViewMapper,
    private val topicoService: TopicoService,
    private val respostaRepository: RespostaRepository,
    private val emailService: EmailService

) {

    fun cadastrar(resposta: Resposta){
        respostaRepository.save(resposta)
        val emailAutor = resposta.topico.autor.email
        emailService.notificar(emailAutor)
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
