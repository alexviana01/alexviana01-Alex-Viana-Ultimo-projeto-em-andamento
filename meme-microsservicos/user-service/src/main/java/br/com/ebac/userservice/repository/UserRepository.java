package br.com.ebac.userservice.repository;

import br.com.ebac.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
