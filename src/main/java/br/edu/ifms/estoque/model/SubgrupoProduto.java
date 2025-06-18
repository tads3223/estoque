/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.model;

import br.edu.ifms.estoque.model.heranca.TablePerClassBase;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

/**
 *
 * @author 1513003
 */
@Entity
@SequenceGenerator(
        sequenceName = "subgrupo_produto_sequence", 
        name = "tablePerClassBase", 
        allocationSize = 1
)
public class SubgrupoProduto extends TablePerClassBase {
    
    @ManyToOne
    private SubgrupoProduto grupoProduto;

    public SubgrupoProduto() {
    }

    public SubgrupoProduto(Long id, String nome, SubgrupoProduto grupoProduto) {
        super(id, nome);
        this.grupoProduto = grupoProduto;
    }
    
    public SubgrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(SubgrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }
    
}
