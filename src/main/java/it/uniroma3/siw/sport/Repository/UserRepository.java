package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

}
