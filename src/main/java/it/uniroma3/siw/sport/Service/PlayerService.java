package it.uniroma3.siw.sport.Service;

import it.uniroma3.siw.sport.Model.Image;
import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Repository.ImageRepository;
import it.uniroma3.siw.sport.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    public PlayerRepository playerRepository;
    @Autowired
    public ImageRepository imageRepository;

    public List<Player> findAllPlayers(){ return playerRepository.findAll();}


    public Player createPlayer(Player player, MultipartFile multipartFile) throws IOException {
        Image playerImage= new Image(multipartFile.getBytes());
        imageRepository.save(playerImage);
        player.setImage(playerImage);
        return playerRepository.save(player);

    }

    public void deletePlayerById(Long id){  playerRepository.deleteById(id);}
}
