package it.uniroma3.siw.sport.Controller;

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

    @GetMapping("/")
    public  String home(){
        return "index";
    }

    @GetMapping("/teams")
    public String getTeams(Model model){
        model.addAttribute("teams", teamService.findAllTeams());
        return "teams";
    }

    @GetMapping("/teams/team/{id}")
    public String viewTeam(Model model , @PathVariable Long id){
        return "team";
    }
}
