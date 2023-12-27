package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.President;
import it.uniroma3.siw.sport.Model.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface PresidentRepository extends CrudRepository<President,Long> {
    President findPresidentById(Long id);
    List<President> findAll();

}
