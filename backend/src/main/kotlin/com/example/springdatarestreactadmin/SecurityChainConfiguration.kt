package com.example.springdatarestreactadmin

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.savedrequest.HttpSessionRequestCache

@Configuration
class SecurityChainConfiguration {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        val requestCache = HttpSessionRequestCache()
        requestCache.setMatchingRequestParameterName(null)
        http.csrf()
            .disable()
            .requestCache { cache ->
                cache.requestCache(requestCache)
            }
            .authorizeHttpRequests { authz ->
                authz
                    .requestMatchers("/login", "/logout").permitAll()
                    .anyRequest().authenticated()
            }
            .formLogin { login ->
                login
                    .successHandler { _, res, auth ->
                        res.status = HttpStatus.OK.value()
                        res.writer.write(ObjectMapper().writeValueAsString(auth.principal))
                    }
                    .permitAll()
                    .defaultSuccessUrl("http://localhost:5173/auth-callback")
                    .failureHandler(SimpleUrlAuthenticationFailureHandler())
            }
            .logout { logout ->
                logout
                    .logoutSuccessHandler { _, res, _ ->
                        res.status = HttpStatus.NO_CONTENT.value()
                    }
                    .permitAll()
                    .deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
            }
            .exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

        return http.build()
    }

}