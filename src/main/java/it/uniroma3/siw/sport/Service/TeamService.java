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
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
    PresidentService presidentService;

    public List<Team> findAllTeams(){ return teamRepository.findAll();}
    public Team findTeamById(Long id){ return  teamRepository.findTeamById(id);}

    public Team createTeam(Team team, MultipartFile multipartFile) throws IOException {
        Image teamImage = new Image(multipartFile.getBytes());
        imageRepository.save(teamImage);
        team.setImage(teamImage);
        return teamRepository.save(team);

    }

    public Team saveTeam(Team team){ return teamRepository.save(team);}

    public void deleteTeamById(Long id){  teamRepository.deleteById(id);}

    //metodo di supporto
    public void function (Model model, Team team ,String username){
        List<Player> playersOfTeam = new ArrayList<>();
        if(team.getPlayers()!= null)
            playersOfTeam.addAll(team.getPlayers());
        playersOfTeam.remove(null);
        model.addAttribute("players", playersOfTeam);
        model.addAttribute("team", team);
        if(username!=null && presidentService.isCurrentUserPresidentOfTeam(team,username))
            model.addAttribute("isPresidentOfTeam" , true);
        else
            model.addAttribute("isPresidentOfTeam", false);
        model.addAttribute("player", new Player());
        model.addAttribute("hasPlayers", !team.getPlayers().isEmpty());
    }


}
