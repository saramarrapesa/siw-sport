package it.uniroma3.siw.sport.Validator;

import it.uniroma3.siw.sport.Model.Player;
import it.uniroma3.siw.sport.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlayerValidator implements Validator {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Player.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Player player = (Player) target;
        if(player.getFirstname() != null &&
                player.getLastname() != null &&
                player.getDate() != null &&
                player.getPlace() != null &&
                player.getRole().getName() != null &&
                playerRepository.existsByFirstnameAndLastnameAndDateAndPlaceAndRole(player.getFirstname(), player.getLastname(), player.getDate(),player.getPlace(), player.getRole())){
            errors.reject("player.duplicate");
        }
    }
}
