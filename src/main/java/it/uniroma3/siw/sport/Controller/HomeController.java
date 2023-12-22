package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.Credentials;
import it.uniroma3.siw.sport.Model.User;
import it.uniroma3.siw.sport.Service.PlayerService;
import it.uniroma3.siw.sport.Service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    TeamService teamService;
    @Autowired
    PlayerService playerService;

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

    @GetMapping("/teams/team/{id}")
    public String viewTeam(Model model , @PathVariable Long id){
        model.addAttribute("team", teamService.findTeamById(id));
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        model.addAttribute("players", playerService.findAllPlayers());
        return "team";
    }
}
