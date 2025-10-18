/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.service;

import br.edu.ifms.estoque.dto.UsuarioRegisterRequest;
import br.edu.ifms.estoque.mapper.UsuarioMapper;
import br.edu.ifms.estoque.model.Usuario;
import br.edu.ifms.estoque.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author 1513003
 */
@RequiredArgsConstructor
@Service
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) 
            throws UsernameNotFoundException {
        
        var optional = repository.findById(username);
        
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new UsernameNotFoundException("Usuário não encontrado");
    }
    
    @Transactional
    public Usuario save(UsuarioRegisterRequest req) {
        var entity = UsuarioMapper.INSTANCE.toEntity(req);
        return repository.save(entity);
    }
    
}
