package br.com.felipe.forum.controller

import br.com.felipe.forum.dto.RespostaForm
import br.com.felipe.forum.service.RespostaService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/resposta")
class RespostaController (private val service: RespostaService) {

    @PostMapping
    fun cadastrar(@PathVariable id: Long, @RequestBody @Valid dto: RespostaForm) {
        service.cadastrar(dto,id)
    }
}