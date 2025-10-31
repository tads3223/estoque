/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.admin.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author 1513003
 */
@Service
public class SocialProviderService {

    // Usamos o Repositório do SAS e o JdbcTemplate para funcionalidades extras
    private final RegisteredClientRepository registeredClientRepository;
    private final JdbcTemplate jdbcTemplate;

    // Construtor para inicializar o JdbcTemplate, assumindo que você injeta o DataSource no Service
    // Se o @RequiredArgsConstructor funcionar com seus campos, você pode simplificar
    public SocialProviderService(RegisteredClientRepository registeredClientRepository, DataSource dataSource) {
        this.registeredClientRepository = registeredClientRepository;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // SQLs necessários:
    private static final String DELETE_SQL = "DELETE FROM oauth2_registered_client WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT id, client_id, client_name FROM oauth2_registered_client";

    /**
     * Busca um cliente pelo ID de Registro (o 'id' que usamos como
     * 'registrationId').
     *
     * @param registrationId
     * @return
     */
    public Optional<RegisteredClient> findByRegistrationId(String registrationId) {
        // Usa o método padrão do SAS para buscar (que é pelo ID principal da tabela)
        return Optional.ofNullable(registeredClientRepository.findById(registrationId));
    }

    /**
     * Lista todos os provedores sociais (retorna apenas dados básicos).Nota: O
     * RegisteredClientRepository não tem findAll, então usamos JdbcTemplate.
     *
     * @return
     */
    public List<RegisteredClient> findAllProviders() {
        return jdbcTemplate.query(FIND_ALL_SQL, (ResultSet rs, int rowNum) -> RegisteredClient.withId(rs.getString("id"))
                .clientId(rs.getString("client_id"))
                .clientName(rs.getString("client_name"))
                // Adicionamos os Grant Types mínimos para ser válido, mas o foco é listar
                .authorizationGrantType(org.springframework.security.oauth2.core.AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .build() // Criamos um RegisteredClient simples (sem segredos ou settings) para listar
        );
    }

    /**
     * Deleta o provedor social do banco de dados.
     *
     * @param registrationId O ID do provedor (e.g., "google").
     * @return true se deletado, false caso contrário.
     */
    @Transactional // Garante que a operação de deleção seja segura
    public boolean deleteByRegistrationId(String registrationId) {
        int rowsAffected = jdbcTemplate.update(DELETE_SQL, registrationId);
        return rowsAffected > 0;
    }

    /**
     * Salva ou atualiza um RegisteredClient.
     *
     * @param client O objeto RegisteredClient a ser salvo.
     */
    public void save(RegisteredClient client) {
        registeredClientRepository.save(client);
    }
}
