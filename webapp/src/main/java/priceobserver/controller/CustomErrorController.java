package priceobserver.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class CustomErrorController implements ErrorController {

    private static final String ERROR_PAGE = "error/errorPage";
    private static final String MAIN_PAGE = "index";

    @GetMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Optional<HttpStatus> status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .map(Object::toString)
                .map(Integer::parseInt)
                .map(HttpStatus::valueOf);

        if (status.isEmpty()) {
            return MAIN_PAGE;
        }

        populateModel(status.get(), model);
        return ERROR_PAGE;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

    private void populateModel(HttpStatus status, Model model) {
        String errorText;
        switch (status) {
            case NOT_FOUND:
                errorText = "Page not found";
                break;
            case INTERNAL_SERVER_ERROR:
                errorText = "Page temporary unavailable";
                break;
            default:
                errorText = status.getReasonPhrase();
                break;
        }
        model.addAttribute("errorCode", status.value());
        model.addAttribute("errorText", errorText);
    }
}