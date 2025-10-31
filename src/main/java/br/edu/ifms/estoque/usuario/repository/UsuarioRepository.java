/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.usuario.repository;

import br.edu.ifms.estoque.arquitetura.repository.JpaSpecificationRepository;
import br.edu.ifms.estoque.security.enumeration.SocialAuthProvider;
import br.edu.ifms.estoque.usuario.model.Usuario;
import java.util.Optional;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 1513003
 */
@Repository
public interface UsuarioRepository extends JpaSpecificationRepository<Usuario, String> {
    
    Optional<Usuario> findByLoginAndAuthProvider(String login, SocialAuthProvider provider);
    
}
