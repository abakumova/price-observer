package priceobserver.controller.product;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import priceobserver.data.product.ProductService;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.producttype.ProductTypeEnum;
import priceobserver.util.LayoutUtils;

import javax.servlet.RequestDispatcher;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static priceobserver.util.LayoutUtils.NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME;
import static priceobserver.util.LayoutUtils.PRODUCT_LIST_PAGE;
import static priceobserver.util.LayoutUtils.PRODUCT_NOT_FOUND_MESSAGE;
import static priceobserver.util.LayoutUtils.PRODUCT_PAGE;

@Controller
public class ProductViewController {

    private final ProductService productService;

    @Autowired
    public ProductViewController(ProductService productService) {
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
    public String viewProductsByType(@PathVariable("type") String typeStr,
                                     @RequestParam(value = "page", required = false) Integer selectedPage,
                                     Model model) {
        if (selectedPage == null || selectedPage < 1) {
            selectedPage = 1;
        }
        Integer finalSelectedPage = selectedPage;
        ProductTypeEnum.getByName(typeStr).ifPresent(t -> prepareModel(t, model, finalSelectedPage));
        return PRODUCT_LIST_PAGE;
    }

    private void prepareModel(ProductTypeEnum type, Model model, Integer selectedPage) {
        int countOfPages = (int) Math.ceil(productService.getProductCountByType(type) / 9.0);
        model.addAttribute("selectedPage", selectedPage.toString());
        model.addAttribute("pageList", LayoutUtils.getPaginationList(selectedPage, countOfPages));
        model.addAttribute("productsAndPrices", productService.getProductsInfoPageableByType(
                type,
                selectedPage - 1,
                NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME)
        );
        model.addAttribute("type", type.getName());
        model.addAttribute("previousPage", selectedPage - 1 < 1 ? "" : String.valueOf(selectedPage - 1));
        model.addAttribute("nextPage", selectedPage + 1 > countOfPages ? "" : String.valueOf(selectedPage + 1));
    }

    private Map<String, String> getPropertiesMap(ProductDto product) {
        return Optional.ofNullable(product.getProductProperties())
                .map(p -> new JSONObject(p.getProperties()).toMap())
                .map(this::convertMap)
                .orElse(Collections.emptyMap());
    }

    private Map<String, String> convertMap(Map<String, Object> map) {
        Map<String, String> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof String) {
                newMap.put(entry.getKey(), (String) entry.getValue());
            }
        }
        return newMap;
    }
}
