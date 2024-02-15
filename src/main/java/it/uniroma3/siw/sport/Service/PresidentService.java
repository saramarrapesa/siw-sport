package it.uniroma3.siw.sport.Service;

import it.uniroma3.siw.sport.Model.*;
import it.uniroma3.siw.sport.Repository.ImageRepository;
import it.uniroma3.siw.sport.Repository.PresidentRepository;
import it.uniroma3.siw.sport.Repository.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PresidentService {
    @Autowired
    PresidentRepository presidentRepository;
    @Autowired
    ImageRepository imageRepository;
    @Autowired
    TeamRepository teamRepository;
    public List<President> findAllPresidents(){ return presidentRepository.findAll();}
    public President findPresidentById(Long id){ return  presidentRepository.findPresidentById(id);}
    @Transactional
    public President createPresident(President president, MultipartFile multipartFile) throws IOException {
        Image presidentImage = new Image(multipartFile.getBytes());
        imageRepository.save(presidentImage);
        president.setImage(presidentImage);
        return presidentRepository.save(president);

    }
    public void deletePresidentById(Long id){  presidentRepository.deleteById(id);}

    public boolean isCurrentUserPresidentOfTeam(Team team , String username) {

        // Verifica se l'utente autenticato è il presidente della squadra
        return team != null && team.getPresident() != null && team.getPresident().getUsername().equals(username);
    }

}
