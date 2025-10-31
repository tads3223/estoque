/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifms.estoque.tipoLogradouro;

import br.edu.ifms.estoque.arquitetura.dto.DefaultRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author 1513003
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class TipoLogradouroRequest extends DefaultRequest {

    @NotNull(message = "O campo SIGLA não deve ser NULO")
    @NotEmpty(message = "O campo SIGLA não deve ser VAZIO")
    @NotBlank(message = "O campo SIGLA não deve ser preenchido com ESPAÇO")
    private String sigla;
}
