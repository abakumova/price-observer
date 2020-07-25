package priceobserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static priceobserver.controller.ControllersConstants.INDEX_PAGE;

@Controller
public class MainController {

    @GetMapping("/")
    public String viewIndexPage() {
        return INDEX_PAGE;
    }
}
