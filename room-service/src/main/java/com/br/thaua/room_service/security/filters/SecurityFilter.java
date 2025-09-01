package com.br.thaua.room_service.security.filters;

import com.br.thaua.room_service.http.dto.GatewayRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Service
public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String signatureGateway =  request.getHeader("x-signature-gateway");

//        if(signatureGateway == null) {
//            response.sendError(401);
//            filterChain.doFilter(request, response);
//            return;
//        }

        String id = request.getHeader("x-user-id");
        String name = request.getHeader("x-user-name");
        String email = request.getHeader("x-user-email");
        String roles = request.getHeader("x-user-roles");

        if(id == null || name == null || email == null || roles == null) {
            filterChain.doFilter(request, response);
            return;
        }

        List<String> rolesList = List.of(roles.split(","));
        GatewayRequest gatewayRequest = new GatewayRequest(Long.valueOf(id), name, email, rolesList);
        Authentication authentication = new UsernamePasswordAuthenticationToken(gatewayRequest, null, gatewayRequest.roles().stream().map(SimpleGrantedAuthority::new).toList());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
