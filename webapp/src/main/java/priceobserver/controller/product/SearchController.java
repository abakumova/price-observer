package priceobserver.controller.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static priceobserver.util.LayoutUtils.SEARCH_RESULT_LIST_PAGE;

@Controller
public class SearchController {

    @GetMapping("/search")
    public String viewSearchResult() {
        return SEARCH_RESULT_LIST_PAGE;
    }
}
