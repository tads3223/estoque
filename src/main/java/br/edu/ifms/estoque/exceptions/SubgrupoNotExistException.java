/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.exceptions;

/**
 *
 * @author 1513003
 */
public class SubgrupoNotExistException
        extends RuntimeException {

    public SubgrupoNotExistException() {
        super("Subgrupo não existe");
    }

}
