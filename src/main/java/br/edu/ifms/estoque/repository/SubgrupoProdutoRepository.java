/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.repository;

import br.edu.ifms.estoque.model.SubgrupoProduto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 1513003
 */
@Repository
public interface SubgrupoProdutoRepository
        extends JpaRepository<SubgrupoProduto, Long>
{
    @Query(value = """
                   SELECT sb FROM SubgrupoProdut sb
                   WHERE sb.grupoProduto.id IN :gruposIds
                   """)
    public List<SubgrupoProduto> buscarPorGruposdeProdutos(
            @Param("gruposId") List<Long> items);
}
