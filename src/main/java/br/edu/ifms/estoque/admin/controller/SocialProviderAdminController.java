/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.admin.controller;

// DTO para receber dados do frontend
import br.edu.ifms.estoque.security.builder.SocialClientBuilder;
import br.edu.ifms.estoque.security.dto.ProviderRegistrationRequest;
import br.edu.ifms.estoque.admin.service.SocialProviderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author 1513003
 */
@RestController
@RequestMapping("/admin/social-providers")
@RequiredArgsConstructor
public class SocialProviderAdminController {

    private final SocialProviderService socialProviderService; // INJETAMOS O SERVICE

    /**
     * Endpoint para cadastrar um novo Provedor Social (e.g., Facebook, Novo
     * Google App).
     *
     * @param request Dados do provedor a ser cadastrado.
     * @return
     */
    @PostMapping
    public ResponseEntity<String> registerProvider(
            @RequestBody ProviderRegistrationRequest request) {

        // 1. Constrói o objeto RegisteredClient no formato que o SAS espera
        RegisteredClient newClient = SocialClientBuilder.buildClient(request);
        try {
            // 2. Salva no banco de dados (usando o JdbcRegisteredClientRepository configurado)
            socialProviderService.save(newClient);
            return ResponseEntity.status(HttpStatus.CREATED).body("Provedor social '" + request.registrationId() + "' cadastrado com sucesso.");
        } catch (Exception e) {
            // Tratar erro de duplicidade de ID ou outros erros de DB
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar o provedor: " + e.getMessage());
        }
    }

    /**
     * Endpoint para buscar a configuração de um provedor (e.g., "google").
     *
     * @param registrationId A chave de identificação do provedor (e.g.,
     * "google").
     * @return
     */
    @GetMapping("/{registrationId}")
    public ResponseEntity<RegisteredClient> getProvider(@PathVariable String registrationId) {
        return socialProviderService.findByRegistrationId(registrationId)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Provedor não encontrado"));
    }

    /**
     * Endpoint para listar todos os provedores
     *
     * @return
     */
    @GetMapping
    public ResponseEntity<List<RegisteredClient>> getAllProviders() {
        List<RegisteredClient> providers = socialProviderService.findAllProviders();
        return ResponseEntity.ok(providers);
    }

    /**
     * Endpoint para deletar um provedor
     *
     * @param registrationId
     * @return
     */
    @DeleteMapping("/{registrationId}")
    public ResponseEntity<Void> deleteProvider(@PathVariable String registrationId) {
        boolean wasDeleted = socialProviderService.deleteByRegistrationId(registrationId);

        if (wasDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Provedor não encontrado para deleção.");
        }
    }
}
