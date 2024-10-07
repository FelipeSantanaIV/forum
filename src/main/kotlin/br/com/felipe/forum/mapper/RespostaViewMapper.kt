package br.com.felipe.forum.mapper

import br.com.felipe.forum.dto.RespostaView
import br.com.felipe.forum.model.Resposta
import org.springframework.stereotype.Component

@Component
class RespostaViewMapper: Mapper<Resposta, RespostaView> {
    override fun map(r: Resposta): RespostaView {
        return RespostaView(
            id = r.id,
            idAutor = r.autor.id!!,
            idTopico = r.topico.id!!,
            mensagem = r.mensagem,
            solucao = r.solucao
        )
    }

}
