package br.com.felipe.forum.service

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service

@Service
class EmailService (
    private val javaMailSender: JavaMailSender
){

    fun notificar(emailAutor: String){
        val message = SimpleMailMessage()

        message.subject = ("[Alura] Resposta recebida")
        message.text = ("Ola, o seu tópico foi respondido!")
        message.setTo(emailAutor)

        javaMailSender.send(message)
    }
}