package com.Proyecto.Integrador.controller.auth;

import com.Proyecto.Integrador.dto.AuthDto;
import com.Proyecto.Integrador.dto.LoginDto;
import com.Proyecto.Integrador.dto.RegisterDto;
import com.Proyecto.Integrador.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthDto> Login(@RequestBody LoginDto login){
        AuthDto authDto = this.authService.login(login);
        if (authDto == null) {
            return ResponseEntity.status(401).build(); // Unauthorized
        }
        return ResponseEntity.ok(authDto);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthDto> register(@RequestBody RegisterDto dto){
        try {
            AuthDto authDto = this.authService.register(dto);
            return ResponseEntity.ok(authDto);
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new AuthDto(e.getMessage()));
        }
    }
}
