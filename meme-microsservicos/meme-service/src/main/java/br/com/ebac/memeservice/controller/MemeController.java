package br.com.ebac.memeservice.controller;

import br.com.ebac.memeservice.model.Meme;
import br.com.ebac.memeservice.service.MemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memes")
public class MemeController {

    private static final Logger log = LoggerFactory.getLogger(MemeController.class);
    private final MemeService memeService;

    public MemeController(MemeService memeService) {
        this.memeService = memeService;
    }

    @PostMapping
    public ResponseEntity<?> createMeme(@RequestBody Meme meme) {
        log.info("Recebendo requisição para criar meme: {}", meme);
        try {
            Meme savedMeme = memeService.save(meme);
            log.info("Meme criado com sucesso: {}", savedMeme);
            return new ResponseEntity<>(savedMeme, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            log.warn("Falha ao criar meme: {}", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<List<Meme>> getAllMemes() {
        log.info("Recebendo requisição para listar todos os memes");
        List<Meme> memes = memeService.findAll();
        log.info("Encontrados {} memes", memes.size());
        return new ResponseEntity<>(memes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meme> getMemeById(@PathVariable Long id) {
        log.info("Recebendo requisição para buscar meme com ID: {}", id);
        return memeService.findById(id)
                .map(meme -> {
                    log.info("Meme encontrado: {}", meme);
                    return new ResponseEntity<>(meme, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    log.warn("Meme com ID: {} não encontrado", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
