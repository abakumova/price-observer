package priceobserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);
    private static final String ERROR_PAGE = "error/errorPage";

    @GetMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView errorPage = new ModelAndView(ERROR_PAGE);
        String errorCode = "";
        String errorText = "";

        Optional<Integer> status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .map(Object::toString)
                .map(Integer::parseInt);

        if (status.isPresent() && status.get() == HttpStatus.NOT_FOUND.value()) {
            LOGGER.error("ERROR 404 URL {}?{}", request.getRequestURL(), request.getQueryString());
            errorCode = "404";
            errorText = "Page not found";
        } else if (status.isPresent() && status.get() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
            LOGGER.error("ERROR 500 URL {}?{}", request.getRequestURL(), request.getQueryString());
            errorCode = "500";
            errorText = "Page temporary unavailable";
        }

        errorPage.addObject("errorCode", errorCode);
        errorPage.addObject("errorText", errorText);
        return errorPage;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}