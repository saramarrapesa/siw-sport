package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Model.Team;
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
public class PresidentController {


    @Autowired
    PlayerService playerService;
    @Autowired
    PresidentService presidentService;
    @Autowired
    TeamService teamService;

    /*@GetMapping("/president")
    public String presidentHome(Model model){

        return "president/presidentHome";
    }

    @GetMapping("/president/teams")
    public String getTeams(Model model ){
        model.addAttribute("teams", teamService.findAllTeams() );
        return "president/teams";
    }

    @GetMapping("/president/playersInTeam/{teamId}")
    public String getPlayersInTeam(@PathVariable("teamId")Long team_id, Model model){
        Team team = teamService.findTeamById(team_id);
            model.addAttribute("team", team);
            model.addAttribute("players", playerService.findPlayersNotInTeam(team_id));
            return "president/playersInTeam";
    }

    @GetMapping("/president/addPlayer/{teamId}/{playerId}")
    public String addPlayersInTeam(@PathVariable("teamId")Long team_id, @PathVariable("playerId") Long player_id, Model model){
        playerService.addPlayerToTeam(player_id, team_id);
        model.addAttribute("team",  teamService.addPlayerToTeam(player_id, team_id));
        model.addAttribute("players", playerService.findAllPlayers());
        return "redirect:/president/playersInTeam/{teamId}";
    }

    @GetMapping("/president/removePlayer/{teamId}/{playerId}")
    public String removePlayersFromTeam(@PathVariable("teamId") Long team_id, @PathVariable("playerId")Long player_id, Model model){
        this.playerService.removeTeamForPlayer(team_id,player_id);

        model.addAttribute("team", teamService.findTeamById(team_id));
        model.addAttribute("players", this.playerService.findAllPlayers());
        return "redirect:/president/playersInTeam/{teamId}";
    }
*/
   @PostMapping("/president/addPlayer/{teamId}")
    public String postPlayerForTeam(@ModelAttribute("player")Player player, @RequestParam("playerImage") MultipartFile file, Model model, @PathVariable("teamId") Long teamId) throws IOException {
        model.addAttribute("player", playerService.createPlayer(player,file));
        return "redirect:/playersOfTeam";
    }
}
