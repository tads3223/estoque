/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.dto;

import br.edu.ifms.estoque.enumeration.Status;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author 1513003
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponse {
    
    private String login;
    private boolean bloqueado;
    private Status status;
    
    private List<PerfilResponse> perfis;
}
