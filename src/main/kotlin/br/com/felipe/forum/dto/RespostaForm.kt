package br.com.felipe.forum.dto

import jakarta.validation.constraints.NotEmpty

data class RespostaForm (
    @field:NotEmpty
    val mensagem: String,
    val idAutor: Long,
    val idTopico: Long,
    val solucao: Boolean
)
