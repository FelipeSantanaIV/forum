package br.com.felipe.forum.dto

import jakarta.validation.constraints.NotEmpty

data class AtualizacaoRespostaForm (
    @field:NotEmpty
    val mensagem: String,
    val id: Long,
    val solucao: Boolean
)
