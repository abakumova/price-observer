package priceobserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import priceobserver.data.product.ProductService;
import priceobserver.dto.producttype.ProductTypeEnum;

@Controller
public class ProductManageController {

    private static final String PRODUCT_PAGE = "productPage";
    private static final String SEARCH_RESULT_PAGE = "searchResult";

    private final ProductService productService;

    @Autowired
    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product")
    public String viewProducts() {
        return PRODUCT_PAGE;
    }

    @GetMapping("/products/{type}")
    public String viewProductsByType(@PathVariable("type") String typeStr, Model model) {
        ProductTypeEnum.getByName(typeStr).ifPresent(t -> prepareModel(t, model));
        return SEARCH_RESULT_PAGE;
    }

    @GetMapping("/search")
    public String viewSearchResult() {
        return SEARCH_RESULT_PAGE;
    }

    private void prepareModel(ProductTypeEnum type, Model model) {
        model.addAttribute("products", productService.getProductsByType(type));
    }
}
