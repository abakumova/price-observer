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

import static priceobserver.util.LayoutUtils.LOGIN_PAGE;
import static priceobserver.util.LayoutUtils.PROFILE_PAGE;

@Controller
public class UserManageController {

    private final UserService userService;

    @Autowired
    public UserManageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute("user", new UserDto());
        return LOGIN_PAGE;
    }

    @GetMapping("/profile")
    public String viewProfilePage(Principal principal, Model model) {
        UserDto user = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(userService::getByEmail)
                .get()
                .orElseThrow(SecurityException::new);
        model.addAttribute("user", user);
        return PROFILE_PAGE;
    }

    @PostMapping("/registration")
    public String registration(UserDto dto, Model model) {
        Optional<String> email = Optional.ofNullable(dto).map(UserDto::getEmail);

        if (email.isPresent() && !userService.isEmailUsed(email.get())) {
            userService.saveNewUser(dto);
            model.addAttribute("isSuccessfullyRegistered", true);
        } else {
            model.addAttribute("isUsedEmail", true);
        }

        model.addAttribute("user", new UserDto());
        return LOGIN_PAGE;
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(UserDto dto, Model model) {
        userService.updateUser(dto);
        model.addAttribute("user", userService.getByEmail(dto.getEmail()).get());
        return PROFILE_PAGE;
    }
}
