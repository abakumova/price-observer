package priceobserver.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import priceobserver.data.product.ProductService;

import static priceobserver.util.LayoutUtils.NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME;
import static priceobserver.util.LayoutUtils.SEARCH_RESULT_LIST_PAGE;

@Controller
public class SearchController {

    private final ProductService productService;

    @Autowired
    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public String viewSearchResult(@RequestParam(value = "query") String query,
                                   @RequestParam(value = "page", required = false) Integer selectedPage,
                                   Model model) {
        model.addAttribute("productsAndPrices", productService.getProductsByNameOrModelContaining(
                query, 0, NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME));
        model.addAttribute("query", query);
        return SEARCH_RESULT_LIST_PAGE;
    }
}
