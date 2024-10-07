package br.com.felipe.forum.model


import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

@Entity
@EnableAutoConfiguration
data class Curso (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val nome: String,
    val categoria: String)