package br.com.movieflix.controller;

import br.com.movieflix.config.TokenService;
import br.com.movieflix.controller.request.UserLoginRequest;
import br.com.movieflix.controller.request.UserRequest;
import br.com.movieflix.controller.response.UserLoginResponse;
import br.com.movieflix.controller.response.UserRegisterResponse;
import br.com.movieflix.entity.User;
import br.com.movieflix.exception.UsernameOrPasswordInvaldException;
import br.com.movieflix.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.register(userRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(userLoginRequest.email(), userLoginRequest.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();

            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new UserLoginResponse(token));

        } catch (BadCredentialsException exception){
            throw new  UsernameOrPasswordInvaldException("Usuário ou Senha invalidos");
        }
    }

    @RequestMapping(value = "/validate",method = RequestMethod.HEAD)
    public ResponseEntity<Void> validateToken(){
        return ResponseEntity.ok().build();
    }
}
