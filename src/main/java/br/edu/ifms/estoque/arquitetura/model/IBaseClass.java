/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.arquitetura.model;

import java.io.Serializable;

/**
 *
 * @author 1513003
 */
public interface IBaseClass extends Serializable {

    public Long getId();

    public void setId(Long value);

    public String getNome();

    public void setNome(String nome);
}
