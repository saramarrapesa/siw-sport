package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long> {

    public List<Player> findAll();
}
