package com.example.userservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data; // Do Lombok
import java.time.LocalDateTime;

@Entity // 1. Marca esta classe como uma entidade JPA
@Table(name = "users") // 2. Define o nome da tabela no banco de dados
@Data // 3. Anotação do Lombok para gerar Getters, Setters, toString, etc.
public class User {

    @Id // 4. Marca este campo como a chave primária (ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 5. Configura o ID para ser autoincrementado pelo banco
    private Long id;

    private String name;
    private String email;
    private LocalDateTime createdAt; // Data do cadastro

    // Pré-persistência para definir a data de criação automaticamente
    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }
}