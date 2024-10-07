package br.com.felipe.forum.mapper

import br.com.felipe.forum.dto.TopicoForm
import br.com.felipe.forum.model.Topico
import br.com.felipe.forum.service.CursoService
import br.com.felipe.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class TopicoFormMapper (private val cursoService: CursoService,
                        private val usuarioService: UsuarioService): Mapper<TopicoForm, Topico>{

    override fun map(t: TopicoForm): Topico {
        return Topico(
            titulo = t.titulo,
            mensagem = t.mensagem,
            curso = cursoService.buscarPorId(t.idCurso),
            autor = usuarioService.buscarPorId(t.idAutor),
        )
    }

}
