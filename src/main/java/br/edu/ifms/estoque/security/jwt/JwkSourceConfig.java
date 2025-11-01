/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.jwt;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.KeySourceException;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKMatcher;
import com.nimbusds.jose.jwk.JWKSelector;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;

/**
 *
 * @author nicho
 */
@Configuration
@RequiredArgsConstructor
public class JwkSourceConfig {
    
    private final TokenVersionValidator tokenVersionValidator;

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);
        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    // NOVO BEAN: Expondo a Chave Pública
    @Bean
    public RSAPublicKey rsaPublicKey(JWKSource<SecurityContext> jwkSource) throws KeySourceException, JOSEException {

        // O JWKSource tem um método para buscar as chaves.
        // Usaremos uma query vazia para obter todas as chaves
        List<JWK> keys = jwkSource.get(new JWKSelector(new JWKMatcher.Builder().build()), null);

        // Filtra e retorna a primeira chave pública RSA
        return keys.stream()
                .filter(key -> key instanceof RSAKey)
                .map(key -> (RSAKey) key)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Nenhuma chave RSA pública encontrada."))
                .toRSAPublicKey();
    }

    // 4. Decodificador de JWT (para o Resource Server validar o próprio token)
    @Bean
    public JwtDecoder jwtDecoder(RSAPublicKey publicKey) {
        var decoder = NimbusJwtDecoder.withPublicKey(publicKey).build();
        
        // Combina o validador padrão (expiração, issuer) com o seu validador customizado
        // 1. Pega os validadores padrão
        OAuth2TokenValidator<Jwt> defaultValidator = JwtValidators.createDefault();
        
        // 2. Cria um validador composto (default + customizado)
        OAuth2TokenValidator<Jwt> combinedValidator = new DelegatingOAuth2TokenValidator<>(
            defaultValidator, 
            tokenVersionValidator // Adiciona sua lógica aqui
        );

        // 3. Define o validador no decoder
        decoder.setJwtValidator(combinedValidator);
        return decoder;
    }

    // Expõe o JwtEncoder usando o JWKSource
    @Bean
    public JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource) {
        // O NimbusJwtEncoder usa o JWKSource (que contém sua chave privada RSA) 
        // para assinar o JWT
        return new NimbusJwtEncoder(jwkSource);
    }
}
