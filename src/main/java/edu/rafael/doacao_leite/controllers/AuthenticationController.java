package edu.rafael.doacao_leite.controllers;

import edu.rafael.doacao_leite.controllers.dtos.AuthDto;
import edu.rafael.doacao_leite.controllers.dtos.LoginResponseDto;
import edu.rafael.doacao_leite.entities.Users;
import edu.rafael.doacao_leite.repositories.UsersRepository;
import edu.rafael.doacao_leite.security.TokenService;
import edu.rafael.doacao_leite.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsersRepository usersRepository;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody AuthDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        var user = usersRepository.getEmail(data.email());

        return ResponseEntity.status(HttpStatus.CREATED).body(new LoginResponseDto(token, user.getId().toString()));
    }
    //registro de usuário na classe UsersController(UsersService) com a rota POST /users/create
}
