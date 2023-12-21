package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.Team;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team,Long> {

    @Override
    public List<Team> findAll();
    public Team findTeamById(Long id);
}
