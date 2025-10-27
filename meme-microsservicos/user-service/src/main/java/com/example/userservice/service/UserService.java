package com.example.userservice.service;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service // 1. Indica que esta é uma classe de serviço gerenciada pelo Spring
public class UserService {

    private final UserRepository userRepository;

    @Autowired // 2. Injeção de dependência do nosso repositório
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retorna todos os usuários cadastrados.
     * @return Lista de todos os usuários.
     */
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Busca um usuário pelo seu ID.
     * @param id O ID do usuário a ser buscado.
     * @return O usuário, se encontrado.
     */
    public User findUserById(Long id) {
        // .orElse(null) é uma forma simples de retornar nulo se o usuário não for encontrado.
        // Em um projeto real, seria melhor lançar uma exceção customizada (ex: UserNotFoundException).
        return userRepository.findById(id).orElse(null);
    }

    /**
     * Salva um novo usuário no banco de dados.
     * @param user O objeto User a ser salvo.
     * @return O usuário salvo com o ID e a data de cadastro preenchidos.
     */
    public User saveUser(User user) {
        // 3. Regra de negócio: garantir que a data de cadastro seja sempre a data atual
        user.setCreatedAt(LocalDateTime.now());
        return userRepository.save(user);
    }
}