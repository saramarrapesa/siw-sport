package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long> {

    @Override
    public List<Player> findAll();
    Player findPlayerById(Long id);

    @Query(value = "select * "
    +"from player p "
    +"where p.id not in "
    +"(select players_id "
    +"from team_players "
    +"where team_players.team_id = team_id) ", nativeQuery = true)
    Iterable<Player> findPlayersNotInTeam(@Param("teamId") Long team_id);

    @Query(value = "select * "
            +"from player p "
            +"where p.id in "
            +"(select players_id "
            +"from team_players "
            +"where team_players.team_id = team_id) ", nativeQuery = true)
    Iterable<Player> findPlayersInTeam(@Param("teamId") Long team_id);

}
