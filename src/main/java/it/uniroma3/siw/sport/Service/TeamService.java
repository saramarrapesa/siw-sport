package it.uniroma3.siw.sport.Service;

import it.uniroma3.siw.sport.Controller.GlobalController;
import it.uniroma3.siw.sport.Model.Image;
import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Model.President;
import it.uniroma3.siw.sport.Model.Team;
import it.uniroma3.siw.sport.Repository.ImageRepository;
import it.uniroma3.siw.sport.Repository.PlayerRepository;
import it.uniroma3.siw.sport.Repository.PresidentRepository;
import it.uniroma3.siw.sport.Repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class TeamService {
    @Autowired
    TeamRepository teamRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    GlobalController globalController;
    @Autowired
    PresidentRepository presidentRepository;

    public List<Team> findAllTeams(){ return teamRepository.findAll();}
    public Team findTeamById(Long id){ return  teamRepository.findTeamById(id);}

    public Team createTeam(Team team, MultipartFile multipartFile) throws IOException {
        Image teamImage = new Image(multipartFile.getBytes());
        imageRepository.save(teamImage);
        team.setImage(teamImage);
        return teamRepository.save(team);

    }

    public void deleteTeamById(Long id){  teamRepository.deleteById(id);}

    /*
     * Metodi per aggiungere , rimuovere e trovare giocatori in una squadra.
     * Solo il presidente della federazione può compiere queste operazioni
     * */

   /* @Transactional
    public Team addPlayerToTeam(Long player_id, Long team_id){
        Team team = teamRepository.findTeamById(team_id);
        Player player = playerRepository.findPlayerById(player_id);
        if(player!=null && team!=null){
            List<Player> players = team.getPlayers();
            players.add(player);
            team.setPlayers(players);
        }
        return teamRepository.save(team);
    }

    @Transactional
    public Team removePlayer (Long player_id, Long team_id){
        Team team = teamRepository.findTeamById(team_id);
        Player player = playerRepository.findPlayerById(player_id);
        if(player!=null && team!=null) {
            List<Player> players = team.getPlayers();
            players.remove(player);
            team.setPlayers(players);
        }
        return teamRepository.save(team);
    }*/


}
