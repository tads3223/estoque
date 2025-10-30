/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.enumeration.Status;
import br.edu.ifms.estoque.model.Perfil;
import br.edu.ifms.estoque.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

/**
 *
 * @author 1513003
 */
@Service
@RequiredArgsConstructor
public class GoogleSocialUserService extends OidcUserService {

    private final UsuarioService usuarioService;
    private final RestClient restClient;

    /**
     * Faz o download de uma imagem a partir de uma URL e retorna como um array
     * de bytes.
     *
     * @param imageUrl A URL da imagem.
     * @return O array de bytes da imagem, ou null em caso de falha.
     */
    public byte[] downloadImage(String imageUrl) {
        try {
            // O RestClient facilita o tratamento de códigos de status 2xx (sucesso)
            byte[] imageBytes = restClient.get()
                    .uri(imageUrl)
                    .retrieve()
                    // Lança exceção se o status for 4xx ou 5xx
                    .body(byte[].class);

            return imageBytes;
        } catch (Exception e) {
            // Captura falhas de conexão ou erros HTTP (que viram exceções)
            System.err.println("Falha ao fazer download da imagem: " + e.getMessage());
            return null;
        }
    }

    @Transactional
    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        // Chama a implementação padrão para obter o usuário do Google
        OidcUser oidcUser = super.loadUser(userRequest);

        // O Google garante que o e-mail será fornecido e único (geralmente)
        String email = oidcUser.getEmail();

        // 1. VERIFICAR: Busca o usuário pelo e-mail (ou outro identificador único);
        var usuario = usuarioService.loadUserByUsername(email);

        // 2. PROVISIONAR (Criar a conta JIT)
        if (usuario == null) {
            /**
             * Usuário não encontrado. Criando nova conta JIT
             */
            // Mapeia as claims do Google para sua entidade User
            var newUser = Usuario.builder()
                    .login(email)
                    .bloqueado(false)
                    .status(Status.ATIVO)
                    .build();
            // Define a Role inicial (Ex: ROLE_USER)
            var perfil = Perfil.builder()
                    .id(1L)
                    .nome("ROLE_USUARIO")
                    .build();
            newUser.add(perfil);

            // Salva no PostgreSQL
            usuarioService.save(newUser);

            // O objeto retornado deve ser o que o Spring usa para as permissões (OidcUser)
        } else {
            // Opcional: Atualizar dados (nome, foto) do usuário existente
        }

        // 3. RETORNA: Retorna o objeto OidcUser que será usado pelo Spring Security
        return oidcUser;
    }
}