package priceobserver.controller;

import org.json.JSONObject;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProductManageController {

    private static final String PRODUCT_PAGE = "productPage";
    private static final String SEARCH_RESULT_PAGE = "searchResult";

    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Oops, the product you're looking for isn't found";

    private final ProductService productService;

    @Autowired
    public ProductManageController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public ModelAndView viewProducts(@PathVariable("id") String id) {
        ModelAndView modelAndView;
        if (id != null) {
            Optional<ProductDto> productOpt = productService.getOneById(Long.parseLong(id));
            if (productOpt.isPresent()) {
                modelAndView = new ModelAndView(PRODUCT_PAGE);
                ProductDto product = productOpt.get();
                modelAndView.addObject("product", product);
                modelAndView.addObject("propertiesMap", getPropertiesMap(product));
                return modelAndView;
            }
        }

        modelAndView = new ModelAndView("forward:/error");
        modelAndView.addObject(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
        modelAndView.addObject(RequestDispatcher.ERROR_MESSAGE, PRODUCT_NOT_FOUND_MESSAGE);
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
        int countOfPages = (int) Math.ceil(productService.getProductCountByType(type) / 9.0);
        model.addAttribute("selectedPage", 2);
        model.addAttribute("pageCount", countOfPages);
        model.addAttribute("products", productService.getProductsPageableByType(type, 0, 9));
    }

    private Map<String, String> getPropertiesMap(ProductDto product) {
        return Optional.ofNullable(product.getProductProperties())
                .map(p -> new JSONObject(p.getProperties()).toMap())
                .map(this::convertMap)
                .orElse(Collections.emptyMap());
    }

    private Map<String,String> convertMap(Map<String,Object> map) {
        Map<String,String> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if(entry.getValue() instanceof String){
                newMap.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return newMap;
    }
}
