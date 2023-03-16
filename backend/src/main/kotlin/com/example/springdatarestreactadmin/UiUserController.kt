package com.example.springdatarestreactadmin

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

/**
 * return logged in user if authenticated, else 401
 * used for checkAuth in authProvider.tsx
 */
@RestController
class UiUserController {

    @GetMapping("/user")
    fun user(user: Principal?): Principal? {
            return user
    }
}