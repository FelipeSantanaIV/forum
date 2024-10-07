package br.com.felipe.forum.mapper

import br.com.felipe.forum.dto.RespostaForm
import br.com.felipe.forum.model.Resposta
import br.com.felipe.forum.service.TopicoService
import br.com.felipe.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class RespostaFormMapper(
    private val usuarioService: UsuarioService,
    private val topicoService: TopicoService
): Mapper<RespostaForm, Resposta> {
    override fun map(r: RespostaForm): Resposta {
        return Resposta(
            mensagem = r.mensagem,
            autor = usuarioService.buscarPorId(r.idAutor),
            topico = topicoService.obterTopico(r.idTopico),
            solucao = r.solucao
        )
    }

}
