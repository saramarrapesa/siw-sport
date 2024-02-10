package it.uniroma3.siw.sport.Repository;

import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PlayerRepository extends CrudRepository<Player,Long> {

    @Override
    public List<Player> findAll();
    Player findPlayerById(Long id);

    boolean existsByFirstnameAndLastnameAndDateAndPlaceAndRole(String firstname , String lastname , LocalDate date ,String place,  Role roleEnum);

    @Query("SELECT p FROM Player p WHERE LOWER(p.firstname) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(p.lastname) LIKE LOWER(CONCAT('%', :keyword, '%')) ")
    List<Player> findByKeyword(@Param("keyword") String keyword);

}
