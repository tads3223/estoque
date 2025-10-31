/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.subgrupoProduto;

import br.edu.ifms.estoque.produto.IAgrupoProdutoDTO;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProduto;
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
                   SELECT sb FROM SubgrupoProduto sb
                   WHERE sb.grupoProduto.id IN :gruposId
                   """)
    public List<SubgrupoProduto> buscarPorGruposdeProdutos(
            @Param("gruposId") List<Long> items);
    
    @Query(value = """
                   SELECT
                   count(sb.grupo_produto_id) as quantidade_subgrupos,
                   sb.grupo_produto_id,
                   (SELECT s.nome FROM subgrupo_produto as s where s.id = sb.grupo_produto_id) as NOME
                   FROM SUBGRUPO_PRODUTO as sb
                   GROUP BY sb.grupo_produto_id
                   """,
            nativeQuery = true)
    public List<IAgrupoProdutoDTO> listaAgrupadaPorSubgrupo();
    
//    @Modifying
//    @Query(value = "update subgrupo_produto set deleted = true where id = ?1",
//            nativeQuery = true)
//    public int deleteLogico(Long id);
}
