/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.handlers;

import br.edu.ifms.estoque.security.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author 1513003
 */
@Component
@RequiredArgsConstructor
public class TokenRedirectSuccessHandler implements AuthenticationSuccessHandler {

    private final AuthService autenticacaoService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // 1. Gera o JWT interno (AutenticacaoService é seu gerador de token)
        var jwtToken = autenticacaoService.getToken(authentication); // Supondo que você tem o campo accessToken

        // 2. Cria a URL de redirecionamento para o Frontend, anexando o token
        // 4. Retorna o token como JSON
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_OK);
        
        // Estrutura de resposta simples para o frontend
        String responseBody = objectMapper.writeValueAsString(new TokenResponse(jwtToken));
        response.getWriter().write(responseBody);
    }
    
    // Classe helper para serialização
    private record TokenResponse(String token) {}
}
