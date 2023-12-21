package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.President;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PresidentRepository extends CrudRepository<President,Long> {
    President findPresidentById(Long id);
    List<President> findAll();
}
