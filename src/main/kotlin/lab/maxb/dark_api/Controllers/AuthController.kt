package lab.maxb.dark_api.Controllers

import lab.maxb.dark_api.Security.Services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("auth")
class AuthController @Autowired constructor(
    private val authService: AuthService,
) {

    @PostMapping("login")
    fun login(@RequestBody body: AuthService.AuthRequest)
        = wrapResponse(authService.login(body))

    @PostMapping("signup")
    fun signup(@RequestBody body: AuthService.AuthRequest)
        = wrapResponse(authService.signup(body))

    private fun wrapResponse(response: AuthService.AuthResponse?)
        = response?.let {
            ResponseEntity.ok(response)
        } ?: ResponseEntity.badRequest().build()
}