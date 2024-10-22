package br.com.felipe.forum.config

import br.com.felipe.forum.security.JWTAuthenticationFilter
import br.com.felipe.forum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val configuration: AuthenticationConfiguration,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
){

   @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.GET, "/topicos").hasAuthority("LEITURA_E_ESCRITA")
                    .requestMatchers("/resposta").hasAuthority("LEITURA_E_ESCRITA")
                    .requestMatchers("/relatorios").hasAuthority("ADMIN")
                    .requestMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/webjars/swagger-ui/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(JWTLoginFilter(authenticationManager = configuration.authenticationManager, jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.build()
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

    fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(encoder())
    }

}