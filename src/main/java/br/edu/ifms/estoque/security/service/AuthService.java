/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.service;

import br.edu.ifms.estoque.usuario.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.security.dto.LoginRequest;
import br.edu.ifms.estoque.usuario.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class AuthService {

    @Autowired
    private JwtEncoder jwtEncoder;

    /**
     * Ao usar o @Lazy no AuthService, o Spring permite que todas as classes que
     * dependem do SecurityConfiguration (incluindo o AuthService via
     * TokenRedirectSuccessHandler) sejam construídas, quebrando o ciclo.
     */
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    /**
     * Cria o token após a autenticação
     *
     * @param authentication
     * @return
     */
    public String getToken(Authentication authentication) {
        // 1. Extrai o nome de usuário (Subject)
        String username = authentication.getName();

        // Se for Login Social, o nome de usuário pode vir como o ID.
        // Você pode querer usar o email ou nome, se disponível nos atributos do OAuth2User.
        if (authentication.getPrincipal() instanceof OAuth2User oauth2User) {
            // Tenta obter o email/login. Adapte esta chave ('email' ou 'login') 
            // conforme o provedor (Google, GitHub, etc.)
            username = (String) oauth2User.getAttributes().getOrDefault("email", username);
        }

        // 2. Cria as claims do JWT
        Instant now = Instant.now();
        long expiry = 3600L; // 1 hora

        String authorities = authentication.getAuthorities().stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("http://localhost:8080")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(username) // Usa o nome/email extraído
                .claim("scope", authorities)
                .build();

        // 3. Codifica e retorna o token
        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

    public String getToken(LoginRequest request) {
        try {
            // 1. A linha abaixo chama o UserDetailsService e o PasswordEncoder
            // Se a autenticação falhar, uma exceção AuthenticationException é lançada.
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
            // 2. Cria o JWT com as informações do usuário
            return getToken(authentication);
        } catch (AuthenticationException ex) {
            throw new UsernameNotFoundException("Usuário ou senha Inválidos!");
        }
    }

    public String autenticar(
            HttpServletRequest request,
            HttpServletResponse response,
            LoginRequest loginRequest) {

        try {
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.username(),
                            loginRequest.password()
                    )
            );

            return getToken(authentication);
        } catch (BadCredentialsException ex) {
            throw new UsernameNotFoundException("Usuário ou senha Inválidos!");
        }
    }

    public String register(UsuarioRegisterRequest user) {
        // Encode the user's password
        user.setSenha(passwordEncoder.encode(user.getSenha()));
        // Save the user to the database
        usuarioService.register(user);
        return "Usuário registrado com sucesso";
    }

}
