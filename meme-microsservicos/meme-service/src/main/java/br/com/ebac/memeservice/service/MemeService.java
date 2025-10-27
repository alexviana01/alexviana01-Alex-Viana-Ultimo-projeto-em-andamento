package br.com.ebac.memeservice.service;

import br.com.ebac.memeservice.dto.CategoryDTO;
import br.com.ebac.memeservice.dto.UserDTO;
import br.com.ebac.memeservice.model.Meme;
import br.com.ebac.memeservice.repository.MemeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MemeService {

    private final MemeRepository memeRepository;
    private final RestTemplate restTemplate;

    // URLs dos outros serviços. Em um ambiente real, isso viria de um arquivo de configuração.
    private final String USER_SERVICE_URL = "http://localhost:8081/users/";
    private final String CATEGORY_SERVICE_URL = "http://localhost:8082/categories/";

    public MemeService(MemeRepository memeRepository, RestTemplate restTemplate) {
        this.memeRepository = memeRepository;
        this.restTemplate = restTemplate;
    }

    public Meme save(Meme meme) {
        // 1. Validar se o usuário existe
        try {
            restTemplate.getForObject(USER_SERVICE_URL + meme.getUserId(), UserDTO.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalArgumentException("Usuário com ID " + meme.getUserId() + " não encontrado.");
            }
            throw e;
        }

        // 2. Validar se a categoria existe
        try {
            restTemplate.getForObject(CATEGORY_SERVICE_URL + meme.getCategoryId(), CategoryDTO.class);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new IllegalArgumentException("Categoria com ID " + meme.getCategoryId() + " não encontrada.");
            }
            throw e;
        }

        // 3. Se ambos existem, salvar o meme
        meme.setDataCadastro(LocalDate.now());
        return memeRepository.save(meme);
    }

    public List<Meme> findAll() {
        return memeRepository.findAll();
    }

    public Optional<Meme> findById(Long id) {
        return memeRepository.findById(id);
    }
}
