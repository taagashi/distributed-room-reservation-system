package com.br.thaua.auth_service.filters;

import ch.qos.logback.core.util.SimpleInvocationGate;
import com.br.thaua.auth_service.domain.Role;
import com.br.thaua.auth_service.http.dto.gateway.AuthGatewayRequest;
import com.br.thaua.auth_service.security.details.AuthDetails;
import com.sun.net.httpserver.Headers;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.hc.client5.http.entity.mime.Header;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

@Service
public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      String signatureGateway = request.getHeader("x-signature-gateway");

      if(signatureGateway == null) {
          response.sendError(401);
          filterChain.doFilter(request, response);
          return;
      }

      String id = request.getHeader("x-user-id");
      String name = request.getHeader("x-user-name");
      String email = request.getHeader("x-user-email");
      String roles = request.getHeader("x-user-roles");
      String token = request.getHeader("Authorization");

      if(id == null || name == null || email == null || roles == null || token == null) {
          filterChain.doFilter(request, response);
          return;
      }

        List<String> rolesList = Stream.of(roles.split(",")).map(role -> "ROLE_" + role).toList();
        AuthGatewayRequest authGatewayRequest = new AuthGatewayRequest(Long.valueOf(id), name, email, rolesList);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authGatewayRequest, null, authGatewayRequest.roles().stream().map(SimpleGrantedAuthority::new).toList());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);
    }
}
