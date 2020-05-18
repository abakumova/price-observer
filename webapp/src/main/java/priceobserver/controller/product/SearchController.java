package priceobserver.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import priceobserver.data.product.ProductService;
import priceobserver.util.LayoutUtils;

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
    public String viewSearchResult(@RequestParam(value = "query", required = false) String query,
                                   @RequestParam(value = "page", required = false) Integer selectedPage,
                                   Model model) {
        if (selectedPage == null || selectedPage < 1) {
            selectedPage = 1;
        }
        if (query == null || query.isBlank()) {
            model.addAttribute("query", "");
        } else {
            prepareModel(query.trim(), model, selectedPage);
        }

        return SEARCH_RESULT_LIST_PAGE;
    }

    private void prepareModel(String query, Model model, Integer selectedPage) {
        long countOfProducts = productService.getProductsByNameOrModelContaining(query);
        model.addAttribute("singleProductList", countOfProducts == 1);
        int countOfPages = (int) Math.ceil(countOfProducts / (float) NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME);

        if (countOfPages > 0) {
            LayoutUtils.preparePagination(model, selectedPage, countOfPages);
            model.addAttribute("productsAndPrices", productService.getProductsByNameOrModelContaining(
                    query, selectedPage - 1, NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME));
        }

        model.addAttribute("query", query);
    }
}