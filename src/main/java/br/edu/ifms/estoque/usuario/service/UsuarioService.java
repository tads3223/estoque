/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.usuario.service;

import br.edu.ifms.estoque.security.enumeration.SocialAuthProvider;
import br.edu.ifms.estoque.arquitetura.service.ServiceAdapter;
import br.edu.ifms.estoque.usuario.dto.UsuarioCreateRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.usuario.dto.UsuarioResponse;
import br.edu.ifms.estoque.usuario.dto.UsuarioUpdateRequest;
import br.edu.ifms.estoque.usuario.mapper.UsuarioMapper;
import br.edu.ifms.estoque.usuario.model.Usuario;
import br.edu.ifms.estoque.usuario.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 1513003
 */
@Service
public class UsuarioService 
        extends ServiceAdapter<Usuario, String, UsuarioResponse, UsuarioCreateRequest, UsuarioUpdateRequest>
        implements UserDetailsService {

    @Autowired
    public UsuarioService(UsuarioRepository repository) {
        super.repository = repository;
        super.mapper = UsuarioMapper.INSTANCE;
    }
    
    @Override
    public Usuario loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        
        var optional = repository.findById(username);
        
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
    
    public Optional<Usuario> buscarPor(String id, SocialAuthProvider provider) {
        var repo = (UsuarioRepository) repository;
        return repo.findByLoginAndAuthProvider(id, provider);
    }
    
    @Transactional
    public Usuario save(Usuario req) {
        return repository.save(req);
    }
    
    @Transactional
    public Usuario register(UsuarioRegisterRequest request) {
        var entity = UsuarioMapper.INSTANCE.toEntity(request);
        return save(entity);
    }
    
}
