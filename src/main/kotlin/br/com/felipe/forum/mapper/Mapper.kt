package br.com.felipe.forum.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}