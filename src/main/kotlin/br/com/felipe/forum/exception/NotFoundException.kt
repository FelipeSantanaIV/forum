package br.com.felipe.forum.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {

}