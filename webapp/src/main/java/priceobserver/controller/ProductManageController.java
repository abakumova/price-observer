package priceobserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import priceobserver.data.product.ProductService;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.producttype.ProductTypeEnum;

import javax.servlet.RequestDispatcher;
import java.util.Optional;

@Controller
public class ProductManageController {

    private static final String PRODUCT_PAGE = "productPage";
    private static final String SEARCH_RESULT_PAGE = "searchResult";

    private final ProductService productService;

    @Autowired
    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ModelAndView viewProducts(@PathVariable("id") String id) {
        ModelAndView modelAndView;
        if (id != null) {
            Optional<ProductDto> product = productService.getOneById(Long.parseLong(id));
            if (product.isPresent()) {
                modelAndView = new ModelAndView(PRODUCT_PAGE);
                modelAndView.addObject("product", product.get());
                return modelAndView;
            }
        }

        modelAndView = new ModelAndView("forward:/error");
        modelAndView.addObject(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
        modelAndView.addObject(RequestDispatcher.ERROR_MESSAGE, HttpStatus.NOT_FOUND.getReasonPhrase());
        return modelAndView;
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
