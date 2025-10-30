/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.handlers;

import br.edu.ifms.estoque.service.AuthService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
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
    // O URL do seu aplicativo React/Frontend
    private static final String FRONTEND_URL = "http://localhost:3000";

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        // 1. Gera o JWT interno (AutenticacaoService é seu gerador de token)
        var jwtToken = autenticacaoService.autenticar(authentication); // Supondo que você tem o campo accessToken

        // 2. Cria a URL de redirecionamento para o Frontend, anexando o token
        // Use um parâmetro seguro (ex: 'token')
        String redirectUrl = FRONTEND_URL + "/login/success?token=" + jwtToken;

        // 3. Redireciona o navegador do usuário
        response.sendRedirect(redirectUrl);
    }
}
