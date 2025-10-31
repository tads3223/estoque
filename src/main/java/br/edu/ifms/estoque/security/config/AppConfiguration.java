/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.security.config;

import br.edu.ifms.estoque.bairro.BairroMapper;
import br.edu.ifms.estoque.marca.MarcaMapper;
import br.edu.ifms.estoque.perfil.PerfilMapper;
import br.edu.ifms.estoque.produto.ProdutoMapper;
import br.edu.ifms.estoque.subgrupoProduto.SubgrupoProdutoMapper;
import br.edu.ifms.estoque.tipoContato.TipoContatoMapper;
import br.edu.ifms.estoque.tipoLogradouro.TipoLogradouroMapper;
import br.edu.ifms.estoque.unidadeMedida.UnidadeMedidaMapper;
import br.edu.ifms.estoque.usuario.mapper.UsuarioMapper;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author 1513003
 */
@Configuration
public class AppConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Estoque API")
                        .version("v1.0") // 🚨 Garanta que você não está sobrescrevendo o campo de versão
                        .description("Documentação da API do Sistema de Estoque"));
    }

    @Bean
    public BairroMapper bairroMapper() {
        return BairroMapper.INSTANCE;
    }

    @Bean
    public TipoLogradouroMapper tipoLogradouroMapper() {
        return TipoLogradouroMapper.INSTANCE;
    }

    @Bean
    public TipoContatoMapper tipoContatoMapper() {
        return TipoContatoMapper.INSTANCE;
    }

    @Bean
    public UsuarioMapper usuarioMapper() {
        return UsuarioMapper.INSTANCE;
    }

    @Bean
    public SubgrupoProdutoMapper subgrupoProdutoMapper() {
        return SubgrupoProdutoMapper.INSTANCE;
    }

    @Bean
    public ProdutoMapper produtoMapper() {
        return ProdutoMapper.INSTANCE;
    }

    @Bean
    public PerfilMapper perfilMapper() {
        return PerfilMapper.INSTANCE;
    }

    @Bean
    public MarcaMapper marcaMapper() {
        return MarcaMapper.INSTANCE;
    }

    @Bean
    public UnidadeMedidaMapper unidadeMedidaMapper() {
        return UnidadeMedidaMapper.INSTANCE;
    }

}
