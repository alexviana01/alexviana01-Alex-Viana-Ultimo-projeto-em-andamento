package br.com.ebac.memeservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CategoryDTO {
    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataCadastro;
}
