package priceobserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
public class CustomErrorController implements ErrorController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);
    private static final String PAGE404 = "error/page404";
    private static final String ERROR = "error";

    @GetMapping("/error")
    public String handleError(HttpServletRequest request) {
        Optional<Integer> status = Optional.ofNullable(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .map(Object::toString)
                .map(Integer::parseInt);

        if (status.isPresent() && status.get() == HttpStatus.NOT_FOUND.value()) {
            LOGGER.error(String.format("ERROR 404 URL %s?%s", request.getRequestURL().toString(), request.getQueryString()));
            return PAGE404;
        }

        return ERROR;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}