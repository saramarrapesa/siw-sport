package it.uniroma3.siw.sport.Controller;

import it.uniroma3.siw.sport.Model.Credentials;
import it.uniroma3.siw.sport.Model.User;
import it.uniroma3.siw.sport.Service.CredentialsService;
import it.uniroma3.siw.sport.Service.UserService;
import it.uniroma3.siw.sport.Session.SessionData;
import it.uniroma3.siw.sport.Validator.CredentialsValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    private CredentialsService credentialsService;


    @Autowired
    CredentialsValidator credentialsValidator;

    @Autowired
    SessionData sessionData;

    @PostMapping(value={"/register"})
    public String registerPost(@Valid @ModelAttribute("user") User user,
                               BindingResult userBindingResult, @Valid
                                   @ModelAttribute("credentials") Credentials credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        this.credentialsValidator.validate(credentials,credentialsBindingResult);

        if(!userBindingResult.hasErrors() && !credentialsBindingResult.hasErrors()) {
            userService.saveUser(user);
            credentials.setUser(user);
            credentialsService.saveCredentials(credentials);
            model.addAttribute("user", user);
            return "index";
        }
        return "index";
    }


    @GetMapping(value = "/success")
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credentials credentials = credentialsService.getCredentials(userDetails.getUsername());
        if (credentials.getRole().equals(Credentials.ADMIN_ROLE)) {
            return "admin/adminHome";
        }
       // model.addAttribute("newsletter", new Newsletter());
        model.addAttribute("user", new User());
        model.addAttribute("credentials", new Credentials());
        return "index";
    }

    @RequestMapping(value={"login/oauth2/user"}, method = RequestMethod.GET)
    public String oAuth2Successful(Model model){
        User loggedUser = this.sessionData.getLoggedUser();
        model.addAttribute("user",loggedUser);
        return "index";
    }



}
