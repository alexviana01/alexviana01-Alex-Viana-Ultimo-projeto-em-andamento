package br.com.ebac.memeservice.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UserDTO {
    private Long id;
    private String nome;
    private String email;
    private LocalDate dataCadastro;
}
