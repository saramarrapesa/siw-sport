package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.*;
import it.uniroma3.siw.sport.Service.PlayerService;
import it.uniroma3.siw.sport.Service.PresidentService;
import it.uniroma3.siw.sport.Service.TeamService;
import it.uniroma3.siw.sport.Validator.PlayerValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class PresidentController {

    @Autowired
    PlayerValidator playerValidator;
    @Autowired
    PlayerService playerService;
    @Autowired
    PresidentService presidentService;
    @Autowired
    TeamService teamService;

    @Autowired
    GlobalController globalController;

    @GetMapping("/president/playersAdd/{id}")
    public String getPlayersAdd(Model model , @PathVariable("id") Long teamId){
        model.addAttribute("player", new Player());
        model.addAttribute("roles", Role.values());
        model.addAttribute("team", teamService.findTeamById(teamId));
        return "/president/playersAdd";
    }

     @PostMapping("/president/addPlayer/{id}")
    public String postPlayerForTeam(@Valid @ModelAttribute("player")Player player, BindingResult bindingResult, @RequestParam("playerImage") MultipartFile file, Model model, @PathVariable("id") Long teamId) throws IOException {
         playerValidator.validate(player, bindingResult);
         if (!bindingResult.hasErrors()) {
             Team team = teamService.findTeamById(teamId);
             if (globalController.getUser() != null && !team.getPlayers().contains(player)) {
                 model.addAttribute("player", playerService.createPlayer(player,file,teamId));
                 playerService.savePlayer(player);
                 team.getPlayers().add(player);
             }
             teamService.saveTeam(team);
             teamService.function(model, team, globalController.getUser().getUsername());
             model.addAttribute("user", new User());
             model.addAttribute("roles", Role.values());
             model.addAttribute("credentials", new Credentials());
             return "redirect:/teams/playersOfTeam/{id}";
         }
         else
             return "index";
    }


    @GetMapping("/president/updatePlayer/{playerId}")
    public String updatePlayer(@PathVariable("playerId") Long playerId , Model model){
        Player existingPlayer = playerService.getPlayerById(playerId);
        Player player = new Player();
        player.setId(existingPlayer.getId());
        player.setFirstname(existingPlayer.getFirstname());
        player.setLastname(existingPlayer.getLastname());
        player.setDate(existingPlayer.getDate());
        player.setImage(existingPlayer.getImage());
        player.setRole(existingPlayer.getRole());
        player.setStartTex(existingPlayer.getStartTex());
        player.setEndTex(existingPlayer.getEndTex());
        player.setPlace(existingPlayer.getPlace());
        model.addAttribute("player", player);
        model.addAttribute("roles", Role.values());
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "redirect:/president/playersAdd/{id}";
    }

    @GetMapping("/president/removePlayer/{teamId}/{playerId}")
    public String removePlayerFromTeam(Model model , @PathVariable("teamId") Long teamId, @PathVariable("playerId") Long playerId){
        Team team = teamService.findTeamById(teamId);
        Player player = playerService.getPlayerById(playerId);

        team.getPlayers().remove(player);
        this.playerService.deletePlayer(player);
        teamService.saveTeam(team);
        teamService.function(model,team,globalController.getUser().getUsername());
        return "redirect:/teams/playersOfTeam/{id}";
    }

}
