package br.com.felipe.forum.service

import br.com.felipe.forum.model.Curso
import br.com.felipe.forum.repository.CursoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.Arrays

@Service
class CursoService (private val repository: CursoRepository){

    fun buscarPorId(id: Long): Curso {
        return repository.getOne(id)
    }
}
