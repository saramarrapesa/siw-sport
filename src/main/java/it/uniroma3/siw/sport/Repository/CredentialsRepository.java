package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.Credentials;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialsRepository extends CrudRepository<Credentials,Long> {
    /**
     *
     * @param username
     * @return an Optional for the Credentials with the passed username
     */
    public Optional<Credentials> findByUsername(String username);


}
