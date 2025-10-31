/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.cargo;

/**
 *
 * @author 1513003
 */
public class CargoNotFoundException extends RuntimeException {

    public CargoNotFoundException() {
        super("Cargo não encontrada");
    }
    
}
