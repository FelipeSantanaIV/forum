package br.com.felipe.forum.model

object UsuarioTest {
    fun build() = Usuario(id = 1, nome = "Joao", email = "jvc.martins", password = "123")
    fun buildToToken() = Usuario(1,nome = "Ana da Silva", email = "ana@email.com", password = "123456")
}