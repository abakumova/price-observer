package priceobserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import priceobserver.data.user.UserService;
import priceobserver.dto.user.UserDto;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserManageController {

    private final UserService userService;

    @Autowired
    public UserManageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String viewProfilePage(Principal principal, Model model) {
        UserDto user = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(userService::getByEmail)
                .get()
                .orElseThrow(SecurityException::new);
        model.addAttribute("user", user);
        return "profile";
    }

    @PostMapping("/registration")
    public String registration(UserDto dto) {

        return "";
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(UserDto dto) {

        return "/profile";
    }
}
