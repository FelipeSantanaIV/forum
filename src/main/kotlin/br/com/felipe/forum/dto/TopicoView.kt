package br.com.felipe.forum.dto

import br.com.felipe.forum.model.StatusTopico
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

data class TopicoView (
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val status: StatusTopico,
    val dataCriacao: LocalDateTime,
    val dataAlteracao: LocalDate?
): Serializable
