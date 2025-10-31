/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.adapter;

import br.edu.ifms.estoque.security.enumeration.SocialAuthProvider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.stereotype.Component;

// IMPORTANTE: Use a mesma dependência que o SAS para acessar o DB
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author 1513003
 */
// Esta classe implementa o contrato que o Spring Security OAuth2 Client espera
@Component
public class DbClientRegistrationRepository implements ClientRegistrationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<ClientRegistration> clientRegistrationRowMapper;
    
    // Nome da tabela do SAS
    private static final String FIND_ALL_SQL = 
        "SELECT * FROM oauth2_registered_client";

    public DbClientRegistrationRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.clientRegistrationRowMapper = new ClientRegistrationRowMapper();
    }

    /**
     * Busca um cliente pelo registrationId (usado pelo Spring Security).
     * @param registrationId
     * @return 
     */
    @Override
    public ClientRegistration findByRegistrationId(String registrationId) {
        String sql = FIND_ALL_SQL + " WHERE id = ?";
        // A tabela do SAS usa 'id' como o registrationId
        return this.jdbcTemplate.query(sql, this.clientRegistrationRowMapper, registrationId)
                .stream()
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Método utilitário para obter a lista de todos os clientes sociais ativos.
     * @return 
     */
    public List<ClientRegistration> findAll() {
        return this.jdbcTemplate.query(FIND_ALL_SQL, this.clientRegistrationRowMapper);
    }

    // Mapeador de Linha (Faz a conversão da Entity do DB para o objeto ClientRegistration)
    private static class ClientRegistrationRowMapper implements RowMapper<ClientRegistration> {
        
        @Override
        public ClientRegistration mapRow(ResultSet rs, int rowNum) throws SQLException {
            
            // NOTE: A tabela 'oauth2_registered_client' armazena as configurações
            
            // 1. Constrói o ClientRegistration
            // Os endpoints (authorizationUri, tokenUri, userInfoUri) NÃO ESTÃO NA TABELA!
            // Para provedores sociais, você precisa obtê-los do padrão OpenID Connect
            // ou mapeá-los. Para simplificar, vamos assumir o 'google' e 'github' aqui:
            
            String registrationId = rs.getString("id");
            String clientId = rs.getString("client_id");
            String clientSecret = rs.getString("client_secret");
            
            // 2. Mapeamento dos Provedores Conhecidos (Obrigatório, pois os URIs não estão no DB)
            ClientRegistration.Builder builder;
            var socialAuthProvider = SocialAuthProvider.fromRegistrationId(registrationId);
            
            if (null == socialAuthProvider) {
                // Se não for um provedor conhecido, não o carregue
                return null;
            } else switch (socialAuthProvider) {
                case GOOGLE -> // O Spring Security possui um ClientRegistrations.builder() utilitário,
                    // mas usaremos a forma manual para maior controle:
                    builder = ClientRegistration.withRegistrationId(registrationId)
                            .clientName(rs.getString("client_name"))
                            .clientSecret(clientSecret)
                            .clientId(clientId)
                            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                            .scope("openid", "profile", "email", "address", "phone") // Escopos OIDC
                            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                            .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                            .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                            .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                            .userNameAttributeName("sub");
                case GITHUB -> builder = ClientRegistration.withRegistrationId(registrationId)
                            .clientName(rs.getString("client_name"))
                            .clientSecret(clientSecret)
                            .clientId(clientId)
                            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                            .scope("user:email")
                            .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                            .authorizationUri("https://github.com/login/oauth/authorize")
                            .tokenUri("https://github.com/login/oauth/access_token")
                            .userInfoUri("https://api.github.com/user")
                            .userNameAttributeName("id");
                default -> {
                    // Se não for um provedor conhecido, não o carregue
                    return null;
                }
            }
            
            return builder.build();
        }
    }
}