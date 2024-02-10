package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.*;
import it.uniroma3.siw.sport.Service.PlayerService;
import it.uniroma3.siw.sport.Service.PresidentService;
import it.uniroma3.siw.sport.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class HomeController {

    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;
    @Autowired
    PresidentService presidentService;
    @Autowired
    GlobalController globalController;

    @GetMapping("/")
    public  String home(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "index";
    }

    @GetMapping("/teams")
    public String getTeams(Model model){
        model.addAttribute("teams", teamService.findAllTeams());
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "teams";
    }

    @GetMapping("/teams/playersOfTeam/{id}")
    public String viewPlayers(Model model , @PathVariable Long id){
        model.addAttribute("team", teamService.findTeamById(id));
        model.addAttribute("hasPlayers", !teamService.findTeamById(id).getPlayers().isEmpty());
        model.addAttribute("user", new User());
        model.addAttribute("player", new Player());
        model.addAttribute("roles", Role.values());
        model.addAttribute("credentials", new Credentials());
        if(this.globalController.getUser()!=null && this.globalController.getUser().getUsername()!=null && presidentService.isCurrentUserPresidentOfTeam(teamService.findTeamById(id),globalController.getUser().getUsername()))
            model.addAttribute("isPresidentOfTeam",true);
        else
            model.addAttribute("isPresidentOfTeam",false);
        return "playersOfTeam";
    }

    @GetMapping("/players/player/{id}")
    public String getPlayer(Model model , @PathVariable Long id){
        if(this.globalController.getUser()!=null && this.globalController.getUser().getUsername()!=null && presidentService.isCurrentUserPresidentOfTeam(teamService.findTeamById(id),globalController.getUser().getUsername()))
            model.addAttribute("isPresidentOfTeam",true);
        else
            model.addAttribute("isPresidentOfTeam",false);
        model.addAttribute("player", playerService.getPlayerById(id));
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "player";
    }

    @GetMapping("/players")
    public String getPlayers(Model model, String keyword){
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        if(keyword!= null){
            model.addAttribute("players", playerService.findByKeyword(keyword));
        }
        else {
            model.addAttribute("players", playerService.findAllPlayers());
        }
        return "players";
    }

    @GetMapping("/presidents")
    public String getPresidents(Model model) {
        model.addAttribute("presidents", presidentService.findAllPresidents());
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "presidents";
    }

    @GetMapping("/presidents/president/{id}")
    public String getPresident(Model model , @PathVariable Long id){
        model.addAttribute("president", presidentService.findPresidentById(id));
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "president";
    }
}
