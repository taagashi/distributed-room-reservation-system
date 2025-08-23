package com.br.thaua.reservation_service.restClient.adapters;

import com.br.thaua.reservation_service.core.restClient.RestClientPort;
import com.br.thaua.reservation_service.domain.Auth;
import com.br.thaua.reservation_service.domain.Room;
import com.br.thaua.reservation_service.restClient.dto.AuthResponse;
import com.br.thaua.reservation_service.restClient.dto.RoomResponse;
import com.br.thaua.reservation_service.restClient.mappers.AuthRestClientMapper;
import com.br.thaua.reservation_service.restClient.mappers.RoomRestClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class RestClientAdapter implements RestClientPort {
    private final RestTemplate restTemplate;
    private final AuthRestClientMapper authRestClientMapper;
    private final RoomRestClientMapper roomRestClientMapper;

    @Override
    public Auth fetchAuthById(String email) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-signature-gateway", "signed-by-gateway");

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<AuthResponse> authResponse = restTemplate.exchange("lb://auth-service/api/v1/auth/email?email=" + email,
                HttpMethod.GET,
                httpEntity,
                AuthResponse.class
                );
        return authRestClientMapper.map(authResponse.getBody());
    }

    @Override
    public Room fetchRoomById(Long roomId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("x-signature-gateway", "signed-by-gateway");

        HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<RoomResponse> roomResponse = restTemplate.exchange("lb://room-service/api/v1/room/" + roomId,
                HttpMethod.GET,
                httpEntity,
                RoomResponse.class
        );

        return roomRestClientMapper.map(roomResponse.getBody());
    }

}
