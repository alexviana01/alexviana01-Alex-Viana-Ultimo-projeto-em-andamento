package br.com.ebac.categoryservice.controller;

import br.com.ebac.categoryservice.model.Category;
import br.com.ebac.categoryservice.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private static final Logger log = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        log.info("Recebendo requisição para criar categoria: {}", category);
        Category savedCategory = categoryService.save(category);
        log.info("Categoria criada com sucesso: {}", savedCategory);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        log.info("Recebendo requisição para listar todas as categorias");
        List<Category> categories = categoryService.findAll();
        log.info("Encontradas {} categorias", categories.size());
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        log.info("Recebendo requisição para buscar categoria com ID: {}", id);
        return categoryService.findById(id)
                .map(category -> {
                    log.info("Categoria encontrada: {}", category);
                    return new ResponseEntity<>(category, HttpStatus.OK);
                })
                .orElseGet(() -> {
                    log.warn("Categoria com ID: {} não encontrada", id);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                });
    }
}
