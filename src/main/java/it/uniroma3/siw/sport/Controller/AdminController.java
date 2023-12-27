package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Model.President;
import it.uniroma3.siw.sport.Model.Role;
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
public class AdminController {

    @Autowired
    private PlayerService playerService;
    @Autowired
    private PresidentService presidentService;
    @Autowired
    private TeamService teamService;

    @GetMapping("/admin")
    public  String adminHome(){
        return "admin/adminHome";
    }

    /*
    * Player Section
    * */

    @GetMapping("/admin/players")
    public String getPlayers(Model model){
        model.addAttribute("players" , playerService.findAllPlayers());
        return "admin/players";
    }

    @GetMapping("/admin/players/add")
    public String getPlayersAdd(Model model){
        model.addAttribute("player", new Player());
        model.addAttribute("teams", teamService.findAllTeams());
        model.addAttribute("roles", Role.values());
        return "admin/playersAdd";
    }

    @PostMapping("/admin/players/add")
    public String postPlayersAdd(@ModelAttribute("player") Player player, @RequestParam("playerImage") MultipartFile file, Model model) throws IOException {
        model.addAttribute("player", playerService.createPlayer(player,file));
        return "redirect:/admin/players";
    }

    @GetMapping("/admin/player/delete/{id}")
    public  String deletePlayer(@PathVariable Long id){
        playerService.deletePlayerById(id);
        return "redirect:/admin/players";
    }

    @GetMapping("/admin/player/update/{id}")
    public String updatePlayer(@PathVariable Long id , Model model){
        Player existingPlayer = playerService.getPlayerById(id);
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
        return "admin/playersAdd";
    }

    /*
    * President Section
    * */

    @GetMapping("/admin/presidents")
    public String getPresident(Model model){
        model.addAttribute("presidents" , presidentService.findAllPresidents());
        return "admin/presidents";
    }

    @GetMapping("/admin/presidents/add")
    public String getPresidentsAdd(Model model){
        model.addAttribute("president", new President());
        model.addAttribute("teams", teamService.findAllTeams());
        return "admin/presidentsAdd";
    }

    @PostMapping("/admin/presidents/add")
    public String postPresidentsAdd(@ModelAttribute("president") President president, @RequestParam("presidentImage") MultipartFile file, Model model) throws IOException {
        model.addAttribute("president", presidentService.createPresident(president,file));
        return "redirect:/admin/presidents";
    }

    @GetMapping("/admin/presidents/delete/{id}")
    public  String deletePresident(@PathVariable Long id){
        presidentService.deletePresidentById(id);
        return "redirect:/admin/presidents";
    }

    @GetMapping("/admin/presidents/update/{id}")
    public String updatePresident(@PathVariable Long id , Model model){
        President existingPresident = presidentService.findPresidentById(id);
        President president = new President();
        president.setId(existingPresident.getId());
        president.setFirstname(existingPresident.getFirstname());
        president.setLastname(existingPresident.getLastname());
        president.setCF(existingPresident.getCF());
        president.setImage(existingPresident.getImage());
        president.setPlace(existingPresident.getPlace());
        president.setDate(existingPresident.getDate());
        president.setTeam(existingPresident.getTeam());
        model.addAttribute("president", president);
        model.addAttribute("teams", teamService.findAllTeams());
        return "admin/presidentsAdd";
    }

    /*
    * Team Section
    * */

    @GetMapping("/admin/teams")
    public String getTeam(Model model){
        model.addAttribute("teams" , teamService.findAllTeams());
        return "admin/teams";
    }

    @GetMapping("/admin/teams/add")
    public String getTeamsAdd(Model model){
        model.addAttribute("team", new Team());
        model.addAttribute("presidents",presidentService.findAllPresidents());
        return "admin/teamsAdd";
    }

    @PostMapping("/admin/teams/add")
    public String postTeamsAdd(@ModelAttribute("team") Team team, @RequestParam("teamImage") MultipartFile file, Model model) throws IOException {
        model.addAttribute("team", teamService.createTeam(team,file));

        return "redirect:/admin/teams";
    }

    @GetMapping("/admin/teams/delete/{id}")
    public  String deleteTeam(@PathVariable Long id){
        teamService.deleteTeamById(id);
        return "redirect:/admin/teams";
    }

    @GetMapping("/admin/teams/update/{id}")
    public String updateTeam(@PathVariable Long id , Model model){
        Team existingTeam = teamService.findTeamById(id);
        Team team = new Team();
        team.setId(existingTeam.getId());
        team.setImage(existingTeam.getImage());
        team.setAddress(existingTeam.getAddress());
        team.setName(existingTeam.getName());
        team.setPlayers(existingTeam.getPlayers());
        team.setPresident(existingTeam.getPresident());
        team.setYear(existingTeam.getYear());
        model.addAttribute("team", team);
        model.addAttribute("presidents",presidentService.findAllPresidents());

        return "admin/teamsAdd";
    }

}
