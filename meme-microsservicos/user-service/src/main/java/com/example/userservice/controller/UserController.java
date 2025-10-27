package com.example.userservice.controller;

import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 1. Combina @Controller e @ResponseBody, simplificando a criação de APIs REST
@RequestMapping("/users") // 2. Define o prefixo do caminho para todos os endpoints nesta classe
public class UserController {

    private final UserService userService;

    @Autowired // Injeta a dependência do nosso serviço
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para criar um novo usuário.
     * Mapeado para POST /users
     * @param user Os dados do usuário vêm no corpo da requisição.
     * @return O usuário criado com status 200 OK.
     */
    @PostMapping // 3. Mapeia requisições HTTP POST para este método
    public ResponseEntity<User> createUser(@RequestBody User user) { // 4.
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    /**
     * Endpoint para listar todos os usuários.
     * Mapeado para GET /users
     * @return Uma lista de todos os usuários com status 200 OK.
     */
    @GetMapping // 5. Mapeia requisições HTTP GET para este método
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Endpoint para buscar um usuário pelo ID.
     * Mapeado para GET /users/{id}
     * @param id O ID vem do caminho da URL.
     * @return O usuário, se encontrado, com status 200 OK, ou 404 Not Found caso contrário.
     */
    @GetMapping("/{id}") // 6. O {id} indica uma variável no caminho
    public ResponseEntity<User> getUserById(@PathVariable Long id) { // 7.
        User user = userService.findUserById(id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}