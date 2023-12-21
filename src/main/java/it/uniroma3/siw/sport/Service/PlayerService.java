package it.uniroma3.siw.sport.Service;

import it.uniroma3.siw.sport.Model.Image;
import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Model.Team;
import it.uniroma3.siw.sport.Repository.ImageRepository;
import it.uniroma3.siw.sport.Repository.PlayerRepository;
import it.uniroma3.siw.sport.Repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    public PlayerRepository playerRepository;
    @Autowired
    public ImageRepository imageRepository;
    @Autowired
    TeamRepository teamRepository;

    public List<Player> findAllPlayers(){ return playerRepository.findAll();}


    public Player createPlayer(Player player, MultipartFile multipartFile) throws IOException {
        Image playerImage= new Image(multipartFile.getBytes());
        imageRepository.save(playerImage);
        player.setImage(playerImage);
        return playerRepository.save(player);

    }

    public void deletePlayerById(Long id){  playerRepository.deleteById(id);}

    public Player getPlayerById(Long id){ return playerRepository.findPlayerById(id);}

    /*
    * Metodi per aggiungere , rimuovere e trovare giocatori in una squadra.
    * Solo il presidente della federazione può compiere queste operazioni
    * */

    @Transactional
    public Player addPlayerToTeam(Long player_id, Long team_id){
        Team team = teamRepository.findTeamById(team_id);
        Player player = playerRepository.findPlayerById(player_id);
        List<Player> players = team.getPlayers();
        player.setTeam(team);
        players.add(player);
        team.setPlayers(players);
        return playerRepository.save(player);
    }

    @Transactional
    public Player removeTeamForPlayer(Long team_id, Long player_id){
        Team team = teamRepository.findTeamById(team_id);
        Player player = playerRepository.findPlayerById(player_id);
        List<Player> players = team.getPlayers();
        players.remove(player);
        player.setTeam(null);
        team.setPlayers(players);
        return this.playerRepository.save(player);
    }

    @Transactional
    public List<Player> findPlayersNotInTeam(Long team_id){
        List<Player> addPlayers= new ArrayList<>();
        for(Player p : playerRepository.findPlayersNotInTeam(team_id)) {
            addPlayers.add(p);
        }
        return addPlayers;
    }

}
