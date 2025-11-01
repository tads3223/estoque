/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.security.repository;

import br.edu.ifms.estoque.security.model.PasswordResetToken;
import br.edu.ifms.estoque.usuario.model.Usuario;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author 1513003
 */
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    // Método principal de busca
    Optional<PasswordResetToken> findByToken(String token);

    // Método para limpeza (opcional)
    void deleteByExpiryDateBefore(Instant now);
    
    List<PasswordResetToken> findByUser(Usuario user);

}
