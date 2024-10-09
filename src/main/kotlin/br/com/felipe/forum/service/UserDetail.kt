package br.com.felipe.forum.service

import br.com.felipe.forum.model.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(private val usuario: Usuario): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority>{
        return usuario.role.map { SimpleGrantedAuthority(it.nome) }
    }
    override fun getPassword()= usuario.password
    override fun getUsername() = usuario.email
    override fun isAccountNonExpired() = true
    override fun isAccountNonLocked() = true
    override fun isCredentialsNonExpired() = true
    override fun isEnabled() = true
}