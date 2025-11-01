/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.service;

import br.edu.ifms.estoque.security.adapter.TokenBlacklistRepository;
import br.edu.ifms.estoque.usuario.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.security.dto.LoginRequest;
import br.edu.ifms.estoque.security.model.PasswordResetToken;
import br.edu.ifms.estoque.security.repository.PasswordResetTokenRepository;
import br.edu.ifms.estoque.usuario.controller.exceptions.UsuarioNotFoundException;
import br.edu.ifms.estoque.usuario.mapper.UsuarioMapper;
import br.edu.ifms.estoque.usuario.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.util.UUID;
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
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Service;

/**
 *
 * @author 1513003
 */
@Service
public class AuthService {

    // O tempo de validade do token de redefinição
    private static final long EXPIRATION_TIME_MINUTES = 15;

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository; // Injete este bean

    /**
     * Ao usar o @Lazy no AuthService, o Spring permite que todas as classes que
     * dependem do SecurityConfiguration (incluindo o AuthService via
     * TokenRedirectSuccessHandler) sejam construídas, quebrando o ciclo.
     */
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Autowired
    @Lazy
    private JwtDecoder jwtDecoder;

    /**
     * Cria o token após a autenticação
     *
     * @param authentication
     * @return
     */
    public String getToken(Authentication authentication) {
        // 1. Extrai o nome de usuário (Subject)
        String username = authentication.getName();
        var usuario = usuarioRepository.findById(username)
                .orElseThrow(UsuarioNotFoundException::new);

        // TRATAMENTO DE NULO NO PONTO DE USO (Defensivo)
        String tskClaim = usuario.getTokenSecurityKey();
        if (tskClaim == null) {
            // Isso não deve acontecer com a correção acima, mas se ocorrer,
            // gere a chave imediatamente e salve, ou gere uma temporária.
            usuario.generateNewTokenSecurityKey(); // Força a geração e atualiza o estado
            usuarioRepository.save(usuario);          // Salva para persistir
            tskClaim = usuario.getTokenSecurityKey();
        }

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
                // CLAIM CRÍTICO: Inclui a chave de segurança atual
                .claim("tsk", tskClaim)
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
        var entity = UsuarioMapper.INSTANCE.toEntity(user);
        // Encode the user's password
        entity.setSenha(passwordEncoder.encode(user.getSenha()));
        // Save the user to the database
        usuarioRepository.save(entity);
        return "Usuário registrado com sucesso";
    }

    // Método de logout
    public void logout(String token) {
        // Você precisa decodificar o token para obter o JTI e a data de expiração
        try {
            Jwt jwt = jwtDecoder.decode(token);
            String jti = jwt.getId();
            Instant expiration = jwt.getExpiresAt();

            if (jti != null && expiration != null) {
                tokenBlacklistRepository.blacklistToken(jti, expiration);
            }
        } catch (JwtException e) {
            // Se o token for inválido, apenas ignora
        }
    }

    public void changePassword(String username, String oldPassword, String newPassword) {
        // 1. Busca o usuário
        var user = usuarioRepository.findById(username)
                .orElseThrow(UsuarioNotFoundException::new);

        // 2. Verifica se a senha antiga é válida (USANDO PasswordEncoder)
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new BadCredentialsException("Senha antiga inválida.");
        }

        // 3. Atualiza e salva a nova senha (codificada)
        String newEncodedPassword = passwordEncoder.encode(newPassword);
        user.setSenha(newEncodedPassword);

        // PASSO 4: Invalida tokens antigos gerando uma nova chave de segurança
        user.generateNewTokenSecurityKey();

        usuarioRepository.save(user);
    }

    // ---------------------------------------------
    // ETAPA 1: SOLICITAÇÃO (Forgot Password)
    // ---------------------------------------------
    public void createPasswordResetToken(String emailOrUsername) {

        // 1. Busca o usuário por email ou username
        var user = usuarioRepository.findByIdentifier(emailOrUsername)
                .orElse(null);

        // Medida de Segurança: Retorna OK mesmo se o usuário não for encontrado
        // Isso impede que atacantes descubram e-mails válidos.
        if (user == null) {
            return;
        }

        // 2. Remove tokens antigos para este usuário (opcional, mas limpa)
        tokenRepository.deleteAll(tokenRepository.findByUser(user));

        // 3. Cria e salva o novo token
        String tokenValue = UUID.randomUUID().toString();
        Instant expiration = Instant.now().plusSeconds(EXPIRATION_TIME_MINUTES * 60);

        var resetToken = PasswordResetToken.builder()
                .token(tokenValue)
                .user(user)
                .expiryDate(expiration)
                .build();
        tokenRepository.save(resetToken);

        // 4. (SIMULAÇÃO) Envio de E-mail
        sendResetEmail(user.getEmail(), tokenValue);
    }

    // Método simulado de envio de e-mail
    private void sendResetEmail(String email, String token) {
        // EM PRODUÇÃO, INTEGRE COM JAVA MAILER AQUI.
        // O link que o usuário receberá será: 
        // http://seu-frontend/reset-password?token=TOKEN_GERADO

        System.out.println("-------------------------------------------------------");
        System.out.println("E-mail simulado enviado para: " + email);
        System.out.println("Token de Redefinição: " + token);
        System.out.println("---- ATENÇÃO: Envio de e-mail não implementado ---- ");
        System.out.println("-------------------------------------------------------");
    }

    // ---------------------------------------------
    // ETAPA 2: REDEFINIÇÃO (Reset Password)
    // ---------------------------------------------
    public void resetPassword(String token, String newPassword) {

        // 1. Busca o token e valida sua existência
        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Token de redefinição inválido."));

        // 2. Valida a expiração do token
        if (resetToken.getExpiryDate().isBefore(Instant.now())) {
            // Remove o token expirado
            tokenRepository.delete(resetToken);
            throw new IllegalArgumentException("Token de redefinição expirado.");
        }

        // 3. Atualiza a senha
        var user = resetToken.getUser();
        String encodedPassword = passwordEncoder.encode(newPassword);
        user.setSenha(encodedPassword);

        // INVALIDAÇÃO DE SEGURANÇA: Invalida todos os tokens antigos (Token Versioning)
        user.generateNewTokenSecurityKey();

        usuarioRepository.save(user);

        // 4. Invalida o token de redefinição (uso único)
        tokenRepository.delete(resetToken);
    }
}
