/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.builder;

import br.edu.ifms.estoque.security.dto.ProviderRegistrationRequest;
import java.util.UUID;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;

/**
 *
 * @author 1513003
 */
public class SocialClientBuilder {
    public static RegisteredClient buildClient(ProviderRegistrationRequest request) {
        // O código de construção do RegisteredClient que estava no Controller:
        return RegisteredClient.withId(UUID.randomUUID().toString())
            .clientId(request.clientId())
            .clientSecret(request.clientSecret())
            .clientName(request.providerName())
            .id(request.registrationId()) 
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}") 
            .scope("openid")
            .scope("profile")
            .scope("email")
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build();
    }
}
