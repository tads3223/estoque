/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 1513003
 */
@Repository
public interface TipoLogradouroRepository 
        extends JpaRepository<TipoLogradouro, Long> {
    
    @Query(value = "SELECT tp FROM TipoLogradouro tp")
    public List<TipoLogradouro> listarTiposDeLogradouros();
    
    @Query(value = "SELECT tp FROM TipoLogradouro tp WHERE tp.nome LIKE :nome")
    public List<TipoLogradouro> listarPorNome(@Param("nome") String nome);
    
    @Modifying
    @Query(value = """
                   UPDATE TipoLogradouro SET nome = ?2, sigla = ?3
                   WHERE id = ?1
                   """)
    public Integer atualizar(Long id, String nome, String sigla);
}
