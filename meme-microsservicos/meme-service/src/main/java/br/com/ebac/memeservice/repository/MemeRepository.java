package br.com.ebac.memeservice.repository;

import br.com.ebac.memeservice.model.Meme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemeRepository extends JpaRepository<Meme, Long> {
}
