/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.JoinedBase;
import jakarta.persistence.Entity;

/**
 *
 * @author 1513003
 */
@Entity
public class TipoContato extends JoinedBase {
    
    private String mascara;

    public TipoContato() {
        super();
    }

    public TipoContato(Long id, String nome, String mascara) {
        super(id, nome);
        this.mascara = mascara;
    }

    public TipoContato(String nome, String mascara) {
        super(null, nome);
        this.mascara = mascara;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }
}
