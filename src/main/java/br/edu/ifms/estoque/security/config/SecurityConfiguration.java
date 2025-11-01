/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.config;

import br.edu.ifms.estoque.security.jwt.TokenRedirectSuccessHandler;
import br.edu.ifms.estoque.security.adapter.DbClientRegistrationRepository;
import br.edu.ifms.estoque.security.service.SocialUserService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 *
 * @author nicho
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final TokenRedirectSuccessHandler jwtAuthenticationSuccessHandler;
    private final SocialUserService socialUserService;
    private final DbClientRegistrationRepository dbClientRegistrationRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Adicione este Bean na sua classe SecurityConfig
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    // 3. Configuração do Servidor de Autorização (URLs)
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        // Define o endereço base do seu servidor de autorização
        return AuthorizationServerSettings.builder().issuer("http://localhost:8080/estoque").build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var origins = Arrays.asList("*");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(origins); // dominio do front end
        configuration.setAllowedHeaders(Arrays.asList("Origin", "Accept", "X-Requested-With",
                "Content-Type", "Authorization", "Access-Control-Request-Method",
                "Access-Control-Request-Headers"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    // 1. Cadeia de Filtros para o Servidor de Autorização (Endpoints OAuth2)
    @Bean
    @Order(1) // Ordem de prioridade mais alta
    public SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http,
            JwtDecoder jwtDecoder
    ) throws Exception {
        // 1. Instancia o configurador (sem usar o método de extensão)
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer
                = new OAuth2AuthorizationServerConfigurer();

        // 2. Usa o método with() para aplicar a configuração
        http.with(authorizationServerConfigurer, Customizer.withDefaults());

        // 3. O restante da configuração
        return http
                /**
                 * Configuração do CORS para permitir acesso de aplicações
                 * externas
                 */
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
                .authorizeHttpRequests(authorize
                        -> authorize
                        .anyRequest().authenticated())
                // GARANTIA STATELESS: Para requisições que chegam com o JWT para o Resource Server
                // A sessão ainda será usada para o fluxo de consentimento /oauth2/authorize
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth2
                        -> oauth2.jwt(
                        jwt -> jwt.decoder(jwtDecoder)
                ))
                .exceptionHandling(exceptions
                        -> exceptions.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"))
                )
                .csrf(csrf -> csrf.disable())
                .build();
    }

    // 2. Cadeia de Filtros Padrão (Para sua API e Login Social)
    @Bean
    @Order(2) // Ordem de prioridade mais baixa
    public SecurityFilterChain defaultSecurityFilterChain(
            HttpSecurity http,
            JwtDecoder jwtDecoder
    ) throws Exception {
        // Lista de caminhos públicos, incluindo Swagger/OpenAPI
        String[] publicPaths = {
            "/api/auth/token", // Permite acesso ao endpoint de token para usuários deslogados
            "/api/auth/password/forgot", // Esqueci minha senha
            "/api/auth/password/reset", // Alteração da senha
            "/oauth2/**", // Permite o fluxo de login social OIDC
            "/v3/api-docs/**", // ENDPOINTS DE DEFINIÇÃO
            "/swagger-ui/**" // INTERFACE GRÁFICA
        };

        http
                /**
                 * Configuração do CORS para permitir acesso de aplicações
                 * externas
                 */
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(csrf -> csrf.disable())
                // 2.1 Configurações de acesso à URL
                .authorizeHttpRequests(authorize
                        -> authorize
                        .requestMatchers(publicPaths).permitAll()
                        // Protege endpoints de administração
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                // GARANTIA STATELESS: Para todas as chamadas de API que usam o JWT
                // Note: O formLogin e oauth2Login ainda utilizarão a sessão durante o fluxo de login
                // (que é temporário), mas o acesso à API após o token será sem estado.
                .sessionManagement(session
                        -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 2.2 Configuração de Login (para credenciais locais)
                .formLogin(form
                        -> form
                        // Após o sucesso do form login, use o nosso Handler:
                        .successHandler(jwtAuthenticationSuccessHandler)
                        .permitAll()
                )
                // 2.3 Configuração de Login Social (OAuth2 Client)
                .oauth2Login(oauth2
                        -> oauth2
                        // Injeta o repositório dinâmico de autenticação OpenID
                        .clientRegistrationRepository(dbClientRegistrationRepository)
                        // cadastra o social login se não existir com perfil ROLE_USUSUARIO
                        .userInfoEndpoint(userInfo
                                -> // AQUI: Usamos o método .oidcUserService para serviços OIDC
                                userInfo.oidcUserService(socialUserService)
                        )
                        // Após o sucesso do login social, use o nosso Handler:
                        .successHandler(jwtAuthenticationSuccessHandler)
                )
                // 2.4 Habilita a validação de JWT para proteger endpoints (Resource Server)
                .oauth2ResourceServer(oauth2
                        -> oauth2.jwt(
                        jwt -> jwt.decoder(jwtDecoder)
                ));

        return http.build();
    }
}
