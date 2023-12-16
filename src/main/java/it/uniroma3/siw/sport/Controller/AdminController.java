package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Service.PlayerService;
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

    @GetMapping("/admin")
    public  String adminHome(){
        return "admin/adminHome";
    }

    //payers section
    @GetMapping("/admin/players")
    public String getPlayers(Model model){
        model.addAttribute("players" , playerService.findAllPlayers());
        return "admin/players";
    }

    @GetMapping("/admin/players/add")
    public String getPlayersAdd(Model model){
        model.addAttribute("player", new Player());
        return "admin/playersAdd";
    }

    @PostMapping("/admin/players/add")
    public String postPlayersAdd(@ModelAttribute("player") Player player, @RequestParam("playerImage") MultipartFile file, Model model) throws IOException {
        model.addAttribute("player", playerService.createPlayer(player,file));
        return "redirect:/admin/players";
    }

    @GetMapping("admin/player/delete/{id}")
    public  String deletePlayer(@PathVariable Long id){
        playerService.deletePlayerById(id);
        return "redirect:/admin/players";
    }

}
