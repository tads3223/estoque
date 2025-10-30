/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.config;

import br.edu.ifms.estoque.converter.EstoqueJwtGrantedAuthoritiesConverter;
import br.edu.ifms.estoque.handlers.TokenRedirectSuccessHandler;
import br.edu.ifms.estoque.service.GoogleSocialUserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

/**
 *
 * @author 1513003
 */
//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final LogoutHandler logoutHandler;
    private final TokenRedirectSuccessHandler tokenRedirectSuccessHandler; // NOVO
    private final GoogleSocialUserService googleSocialUserService; // Seu JIT

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bean para converter o JWT nas Authorities (Roles)
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new EstoqueJwtGrantedAuthoritiesConverter());
        return jwtAuthenticationConverter;
    }

    // --- FILTRO 1: API (JWT RESOURCE SERVER) ---
    // Este filtro é o primeiro a ser executado e lida com todas as requisições 
    // autenticadas via JWT. Usa política STATELESS.
    /**
     * Método de configuração de segurança. Método utilizado para configurar a
     * autenticação e a autorização de acesso ao sistema.
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    @Order(1)
    public SecurityFilterChain filterChainApi(HttpSecurity http) throws Exception {

        // Configurações básicas de segurança
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        // Configuração de Autorização da API
        http.authorizeHttpRequests(auth -> {
            try {
                auth
                        // Permite acesso aos endpoints de Autenticação/Login sem JWT
                        .requestMatchers("/api/auth/**", "/login/**", "/oauth2/**").permitAll()
                        // Restringe o acesso aos endpoints administrativos por Regex
                        // (Exemplo do seu código original)
                        .requestMatchers(
                                RegexRequestMatcher.regexMatcher(HttpMethod.POST, "/api/bairro"),
                                RegexRequestMatcher.regexMatcher(HttpMethod.PUT, "/api/bairro/[0-9]+"),
                                RegexRequestMatcher.regexMatcher(HttpMethod.DELETE, "/api/bairro/[0-9]+")
                        ).hasAnyAuthority("ROLE_ADMIN", "ROLE_GERENTE")
                        // Todas as outras requisições requerem autenticação (JWT válido)
                        .anyRequest().authenticated();
            } catch (Exception ex) {
                Logger.getLogger(SecurityConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        /**
         * Ativa o OAuth2 para o token (Recurso Principal)
         */
        http.oauth2ResourceServer(
                conf -> conf.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
        );

        /**
         * Não utiliza gerenciamento de sessões para requisições de API
         */
        http
                .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        // Configuração de Logout
        http.logout(logout -> logout
                .logoutUrl("/api/auth/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .invalidateHttpSession(false)
        );

        return http.build();
    }

    // --- FILTRO 2: LOGIN (SESSÃO/REDIRECIONAMENTO) ---
    // Este filtro é o segundo a ser executado e lida com os fluxos de login 
    // Social e Local que geram o JWT para redirecionamento.
    @Bean
    @Order(2)
    public SecurityFilterChain filterChainLogin(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                // --- 1. CONFIGURAÇÃO DE LOGIN LOCAL (FORM) ---
                .formLogin(form
                        -> form
                        // URL que o formulário POSTará as credenciais
                        .loginProcessingUrl("/api/auth/login-local")
                        // Handler que gera o JWT e redireciona para o frontend
                        .successHandler(tokenRedirectSuccessHandler)
                        // Permite acesso à página/URL de login
                        .permitAll()
                )
                // --- 2. CONFIGURAÇÃO DE LOGIN SOCIAL (GOOGLE) ---
                .oauth2Login(oauth2
                        -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                        // Seu serviço que faz o Provisionamento JIT
                        .oidcUserService(googleSocialUserService)
                        )
                        // Handler que gera o JWT e redireciona para o frontend
                        .successHandler(tokenRedirectSuccessHandler)
                )
                // --- 3. CONFIGURAÇÃO DE SESSÃO (Necessário para o fluxo de redirecionamento) ---
                // A sessão é criada temporariamente para armazenar o estado durante o login/redirect
                .sessionManagement(s
                        -> s
                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                // Permite o acesso a todas as URLs necessárias para iniciar os fluxos de login
                .authorizeHttpRequests(auth
                        -> auth
                        .requestMatchers(
                                "/api/auth/login-local",
                                "/oauth2/**", // URLs de inicio do OAuth2
                                "/login/**" // Outras URLs relacionadas ao login
                        ).permitAll()
                        // Garante que o filtro 2 não interfira nas requisições API
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
