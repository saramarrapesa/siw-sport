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

    public Player savePlayer(Player player){
        return playerRepository.save(player);

    }

    @Transactional
    public Player createPlayer(Player player, MultipartFile multipartFile, Long teamId) throws IOException {
        Image playerImage= new Image(multipartFile.getBytes());
        imageRepository.save(playerImage);
        player.setImage(playerImage);
        player.setTeam(teamRepository.findTeamById(teamId));
        return playerRepository.save(player);

    }

    public Player getPlayerById(Long id){ return playerRepository.findPlayerById(id);}


    public  void deletePlayer (Player player) { playerRepository.delete(player);}

    public List<Player> findByKeyword(String keyword){
        return playerRepository.findByKeyword(keyword);
    }
}
