package br.com.felipe.forum.dto

data class RespostaView (
    val id: Long?,
    val idAutor: Long,
    val idTopico: Long,
    val mensagem: String,
    val solucao: Boolean
)
