package com.example.amazonclone.controllers;

import com.example.amazonclone.dto.UserDto;
import com.example.amazonclone.models.User;
import com.example.amazonclone.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
public class UserController {

    final
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @RequestMapping("/login")
    public String loginForm() { return "users/login";
    }
    @PostMapping("/login")
    public String postLoginForm(@RequestParam("number") String number, Model model) {

        User user=userService.findUserByEmail(number);
        if (user.getEmail().isEmpty()){
            return "users/login?error='0'";
        }
        model.addAttribute("userEmail",user.getEmail());
        return "users/login-ok";
    }



    @GetMapping("/registration")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "users/registration";
    }

    @PostMapping("/registration")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if (existingUser != null)
            result.rejectValue("number", null,
                    "Користувач вже зареєстрований!!!");
        if (!Objects.equals(userDto.getPassword(),userDto.getWatchingPassword()))
            result.rejectValue("watchingPassword", null,
                    "Паролі не співпадають!!!");
        if (result.hasErrors()) {
            System.out.println("userDto = " + userDto + ", result = " + result);
            model.addAttribute("user", userDto);
            return "users/registration";
        }

        userService.saveUserRegistration(userDto);

        return "redirect:/registration?success";
    }
}