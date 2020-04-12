package priceobserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/*
    The class is on the workpiece stage. The magic will be added in further tickets.
    Everything big starts small.
 */
@Controller
public class UserManageController {

    @GetMapping("/login")
    public String viewLoginPage() {
        return "login";
    }

    @GetMapping("/profile")
    public String viewProfilePage() {
        return "profile";
    }
}
