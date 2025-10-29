/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.filter;

import br.edu.ifms.estoque.service.JwtUtil;
import br.edu.ifms.estoque.service.UsuarioService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author 1513003
 */
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws ServletException, IOException {
        /**
         * Captura a informação de autorização anexada ao cabeçalho da
         * requisição
         */
        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        /**
         * Verifica se no cabeçalho consta a autorização no formato Bearer. Caso
         * positivo, extrai o token da requisição
         */
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            /**
             * Extrai o nome do usuário adicionado no token
             */
            username = jwtUtil.extractUsername(jwt);
        }

        /**
         * Caso o usuário exista e não esteja autenticado no sistema, então ele
         * será recuperado sua requisição será registrada como autenticada.
         */
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            /**
             * Recupera os dados do usuário para registro de autentição
             */
            UserDetails userDetails = this.usuarioService.loadUserByUsername(username);
            if (jwtUtil.validateToken(jwt, userDetails)) {
                /**
                 * Cria um token de autenticação no padrão do Spring Security de
                 * acordo com as autorizações do usuário
                 */
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                /**
                 * Registra o usuário/token autenticado no contexto do Spring
                 * Security
                 */
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        chain.doFilter(request, response);
    }
}
