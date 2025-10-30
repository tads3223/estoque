/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.config;

import br.edu.ifms.estoque.handlers.TokenRedirectSuccessHandler;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 *
 * @author nicho
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final TokenRedirectSuccessHandler jwtAuthenticationSuccessHandler;

    // Adicione este Bean na sua classe SecurityConfig
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 1. Cadeia de Filtros para o Servidor de Autorização (Endpoints OAuth2)
    @Bean
    @Order(1) // Ordem de prioridade mais alta
    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
        // 1. Instancia o configurador (sem usar o método de extensão)
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer
                = new OAuth2AuthorizationServerConfigurer();

        // 2. Usa o método with() para aplicar a configuração
        http.with(authorizationServerConfigurer, Customizer.withDefaults());

        // 3. O restante da configuração
        return http
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .exceptionHandling(exceptions
                        -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(authorizationServerConfigurer.getEndpointsMatcher()))
                .build();
    }

    // 2. Cadeia de Filtros Padrão (Para sua API e Login Social)
    @Bean
    @Order(2) // Ordem de prioridade mais baixa
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                // 2.1 Configurações de acesso à URL
                .authorizeHttpRequests(authorize
                        -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/public/**")).permitAll() // Exemplo de endpoint público
                        .anyRequest().authenticated()
                )
                // 2.2 Configuração de Login (para credenciais locais)
                .formLogin(form
                        -> form
                        // Após o sucesso do form login, use o nosso Handler:
                        .successHandler(jwtAuthenticationSuccessHandler)
                        .permitAll()
                )
                // 2.3 Configuração de Login Social (OAuth2 Client)
                .oauth2Login(oauth2 -> 
                        oauth2
                // Após o sucesso do login social, use o nosso Handler:
                .successHandler(jwtAuthenticationSuccessHandler)
                )
                // 2.4 Habilita a validação de JWT para proteger endpoints (Resource Server)
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }

    // 3. Configuração do Servidor de Autorização (URLs)
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        // Define o endereço base do seu servidor de autorização
        return AuthorizationServerSettings.builder().issuer("http://localhost:8080").build();
    }

    // 4. Decodificador de JWT (para o Resource Server validar o próprio token)
    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource, AuthorizationServerSettings authorizationServerSettings) {
        // A melhor prática é usar o método que descobre o JWKS URI
        // O Resource Server usará o URI do Issuer para buscar o JWKS
        // O AuthorizationServerSettings define este Issuer URI
        return org.springframework.security.oauth2.jwt.JwtDecoders.fromIssuerLocation(authorizationServerSettings.getIssuer());
    }

    // Expõe o JwtEncoder usando o JWKSource
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        // O NimbusJwtEncoder usa o JWKSource (que contém sua chave privada RSA) 
        // para assinar o JWT
        return new NimbusJwtEncoder(jwkSource);
    }
}
