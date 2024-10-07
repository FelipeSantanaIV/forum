package br.com.felipe.forum.repository

import br.com.felipe.forum.model.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository: JpaRepository<Topico,Long>{
}