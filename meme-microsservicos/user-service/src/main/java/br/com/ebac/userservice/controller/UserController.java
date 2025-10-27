package br.com.ebac.userservice.controller;

import br.com.ebac.userservice.model.User;
import br.com.ebac.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        log.info("Recebendo requisição para criar usuário: {}", user);
        User savedUser = userService.save(user);
        log.info("Usuário criado com sucesso: {}", savedUser);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        log.info("Recebendo requisição para listar todos os usuários");
        List<User> users = userService.findAll();
        log.info("Encontrados {} usuários", users.size());
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        log.info("Recebendo requisição para buscar usuário com ID: {}", id);
        return userService.findById(id)
                .map(user -> {
                    log.info("Usuário encontrado: {}", user);
                    return new ResponseEntity<>(user, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    log.warn("Usuário com ID: {} não encontrado", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
