/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.usuario.model;

import br.edu.ifms.estoque.security.enumeration.SocialAuthProvider;
import br.edu.ifms.estoque.enumeration.Status;
import br.edu.ifms.estoque.perfil.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author 1513003
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario implements UserDetails {

    @EqualsAndHashCode.Include
    @ToString.Include
    @Id
    private String login;
    private String senha;
    private boolean bloqueado;
    
    private String nome;
    private String email;

    @ToString.Include
    @Enumerated(EnumType.STRING)
    private Status status;
    
    @Enumerated(EnumType.STRING)
    private SocialAuthProvider authProvider;
    
    // Token Security Version (ou Invalidation Key)
    // Este valor muda toda vez que a senha é alterada ou o usuário é deslogado
    @Builder.Default
    private String tokenSecurityKey = UUID.randomUUID().toString();

    @ManyToMany
    @JoinTable(
            name = "perfis_usuario",
            joinColumns = @JoinColumn(
                    name = "login_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "perfil_id",
                    nullable = false
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"login_id", "perfil_id"}
            )
    )
    private List<Perfil> perfis;
    
    // Método para forçar a invalidação de todos os tokens antigos
    public void generateNewTokenSecurityKey() {
        this.tokenSecurityKey = UUID.randomUUID().toString();
    }
    
    public void add(Perfil value) {
        if (this.perfis == null) {
            this.perfis = new ArrayList<>();
        }
        this.perfis.add(value);
    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public boolean isEnabled() {
        return this.bloqueado;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis;
    }

}
