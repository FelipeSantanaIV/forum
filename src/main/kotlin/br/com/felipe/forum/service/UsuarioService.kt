package br.com.felipe.forum.service

import br.com.felipe.forum.model.Curso
import br.com.felipe.forum.model.Usuario
import br.com.felipe.forum.repository.UsuarioRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UsuarioService (private val repository: UsuarioRepository){

    fun buscarPorId(id: Long): Usuario {
        return repository.getOne(id)
    }

}
