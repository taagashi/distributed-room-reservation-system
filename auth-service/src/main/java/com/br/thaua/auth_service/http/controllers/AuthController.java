package com.br.thaua.auth_service.http.controllers;

import com.br.thaua.auth_service.core.services.AuthServicePort;
import com.br.thaua.auth_service.domain.Auth;
import com.br.thaua.auth_service.http.dto.AuthRequestLogin;
import com.br.thaua.auth_service.http.dto.AuthRequestSingIn;
import com.br.thaua.auth_service.http.dto.AuthResponse;
import com.br.thaua.auth_service.http.dto.gateway.AuthGatewayRequest;
import com.br.thaua.auth_service.http.mappers.AuthDtoMappers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/account")
    public ResponseEntity<AuthResponse> getAccount() {
        AuthGatewayRequest currentUser = extractCurrentUser();
        AuthResponse authResponse = authDtoMappers.map(authServicePort.fetchAuthById(currentUser.id()));
        return ResponseEntity.ok(authResponse);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateAccount(@RequestBody AuthRequestSingIn authRequestSingIn) {
        Auth updated = authDtoMappers.map(authRequestSingIn);
        AuthGatewayRequest currentUser = extractCurrentUser();
        return ResponseEntity.ok(authServicePort.updateAuthById(currentUser.id(), updated));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAccount() {
        AuthGatewayRequest authGatewayRequest = extractCurrentUser();
        authServicePort.deleteAuthById(authGatewayRequest.id());
        return ResponseEntity.ok().build();
    }

    private AuthGatewayRequest extractCurrentUser() {
        return (AuthGatewayRequest) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
