package com.example.userservice.repository;

import com.example.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 1. Indica ao Spring que esta é uma interface de repositório
public interface UserRepository extends JpaRepository<User, Long> {
    // 2. A mágica acontece aqui!
    // Não precisamos escrever nenhum método.
    // JpaRepository já nos fornece:
    // - save(User user) -> Salva ou atualiza um usuário
    // - findById(Long id) -> Busca um usuário pelo ID
    // - findAll() -> Retorna todos os usuários
    // - deleteById(Long id) -> Deleta um usuário pelo ID
    // - e muitos outros!
}