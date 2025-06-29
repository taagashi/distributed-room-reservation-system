package com.br.thaua.auth_service.http.controllers;

import com.br.thaua.auth_service.core.services.AuthServicePort;
import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.http.dto.AuthRequestLogin;
import com.br.thaua.auth_service.http.dto.AuthRequestSingIn;
import com.br.thaua.auth_service.http.dto.AuthResponse;
import com.br.thaua.auth_service.http.mappers.AuthDtoMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthServicePort authServicePort;
    private final AuthDtoMappers authDtoMappers;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AuthRequestLogin authRequestLogin) {
        Auth auth = authDtoMappers.map(authRequestLogin);
        return ResponseEntity.ok(authServicePort.login(auth));
    }

    @PostMapping("/singIn")
    public ResponseEntity<String> singIn(@RequestBody AuthRequestSingIn authRequestSingIn) {
        Auth auth = authDtoMappers.map(authRequestSingIn);
        return ResponseEntity.ok(authServicePort.singIn(auth));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthResponse> getAccount(@PathVariable Long id) {
        AuthResponse authResponse = authDtoMappers.map(authServicePort.fetchAuthById(id));
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateAccount(@PathVariable Long id, @RequestBody AuthRequestSingIn authRequestSingIn) {
        Auth auth = authDtoMappers.map(authRequestSingIn);
        return ResponseEntity.ok(authServicePort.updateAuthById(id, auth));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        authServicePort.deleteAuthById(id);
        return ResponseEntity.ok().build();
    }

}
