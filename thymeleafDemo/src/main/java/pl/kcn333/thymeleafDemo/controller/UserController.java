package pl.kcn333.thymeleafDemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.kcn333.thymeleafDemo.model.User;

import java.util.List;

@Controller
public class UserController {

    @Value("${app.countries}")
    private List<String> countries;

    @Value("${app.languages}")
    private List<String> languages;

    @Value("${app.systems}")
    private List<String> systems;



    @GetMapping("/")
    public String showForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("countries", countries);
        model.addAttribute("languageOptions", languages);
        model.addAttribute("systemsOptions", systems);

        return "user-form";
    }

    @PostMapping("/user")
    public String getUser(@ModelAttribute("user") User user){
        System.out.println("user: " + user.getFirstName() + " " + user.getLastName());
        //model.addAttribute("user",user);
        return "show-user";
    }

}
