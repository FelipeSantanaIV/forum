package br.com.felipe.forum.model

import jakarta.persistence.*
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import java.time.LocalDateTime

@Entity
data class Resposta (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val autor: Usuario,
    @ManyToOne
    var topico: Topico,
    val solucao: Boolean
)
