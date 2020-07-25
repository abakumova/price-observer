package priceobserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import priceobserver.data.product.ProductService;
import priceobserver.data.user.UserService;
import priceobserver.dto.user.UserDto;

import java.security.Principal;
import java.util.Optional;

import static priceobserver.controller.ControllersConstants.IS_SUCCESSFULLY_REGISTERED_ATTR;
import static priceobserver.controller.ControllersConstants.IS_USED_EMAIL_ATTR;
import static priceobserver.controller.ControllersConstants.USER_ATTR;
import static priceobserver.controller.ControllersConstants.WISH_LIST_ATTR;
import static priceobserver.util.LayoutUtils.LOGIN_PAGE;
import static priceobserver.util.LayoutUtils.PROFILE_PAGE;

@Controller
public class UserManageController {

    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public UserManageController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/login")
    public String viewLoginPage(Model model) {
        model.addAttribute(USER_ATTR, new UserDto());
        return LOGIN_PAGE;
    }

    @GetMapping("/profile")
    public String viewProfilePage(Principal principal, Model model) {
        UserDto user = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(userService::getByEmail)
                .get()
                .orElseThrow(SecurityException::new);
        model.addAttribute(WISH_LIST_ATTR, productService.getWishProductsListForUserWishId(user.getId()));
        model.addAttribute(USER_ATTR, user);
        return PROFILE_PAGE;
    }

    @PostMapping("/registration")
    public String registration(UserDto dto, Model model) {
        Optional<String> email = Optional.ofNullable(dto).map(UserDto::getEmail);

        if (email.isPresent() && !userService.isEmailUsed(email.get())) {
            userService.saveNewUser(dto);
            model.addAttribute(IS_SUCCESSFULLY_REGISTERED_ATTR, true);
        } else {
            model.addAttribute(IS_USED_EMAIL_ATTR, true);
        }

        model.addAttribute(USER_ATTR, new UserDto());
        return LOGIN_PAGE;
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(UserDto dto, Model model) {
        userService.updateUser(dto);
        model.addAttribute(USER_ATTR, userService.getByEmail(dto.getEmail()).get());
        return PROFILE_PAGE;
    }

    @GetMapping("/addToWishList/{id}")
    public String addToWishList(@PathVariable Long id, Principal principal) {
        UserDto user = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(userService::getByEmail)
                .get()
                .orElseThrow(SecurityException::new);
        productService.addToWishList(id, user.getId());
        return "redirect:/product/" + id;
    }

    @GetMapping("/removeFromWishList/{id}")
    public String removeFromWishList(@PathVariable Long id, Principal principal) {
        UserDto user = Optional.ofNullable(principal)
                .map(Principal::getName)
                .map(userService::getByEmail)
                .get()
                .orElseThrow(SecurityException::new);
        productService.removeFromWishList(id, user.getId());
        return "redirect:/product/" + id;
    }
}
