/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.enumeration.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.UniqueConstraint;
import java.util.List;

/**
 *
 * @author 1513003
 */
@Entity
public class Usuario {
    @Id
    private String login;
    private String senha;
    private boolean bloqueado;
    private Status status;
    
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

    public Usuario() {
    }

    public Usuario(String login, String senha, boolean bloqueado, Status status) {
        this.login = login;
        this.senha = senha;
        this.bloqueado = bloqueado;
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
}
