/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.unidadeMedida;

/**
 *
 * @author 1513003
 */
public class UnidadeMedidaNotFoundException extends RuntimeException {

    public UnidadeMedidaNotFoundException() {
        super("Unidade de Medida não encontrada");
    }
    
}
