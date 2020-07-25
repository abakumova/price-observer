package priceobserver.controller.product;

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
import java.security.Principal;
import java.util.Optional;

import static priceobserver.controller.ControllerUtils.getPropertiesMap;
import static priceobserver.controller.ControllersConstants.PRICES;
import static priceobserver.controller.ControllersConstants.PRODUCT;
import static priceobserver.controller.ControllersConstants.PRODUCT_AND_PRICE_ATTR;
import static priceobserver.controller.ControllersConstants.PROPERTIES_MAP;
import static priceobserver.controller.ControllersConstants.SINGLE_PRODUCT_LIST_ATTR;
import static priceobserver.controller.ControllersConstants.TYPE_ATTR;
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
        Optional<ProductDto> productOpt = productService.getOneById(Long.parseLong(id));
        if (productOpt.isEmpty()) {
            modelAndView = new ModelAndView("forward:/error");
            modelAndView.addObject(RequestDispatcher.ERROR_STATUS_CODE, HttpStatus.NOT_FOUND.value());
            modelAndView.addObject(RequestDispatcher.ERROR_MESSAGE, PRODUCT_NOT_FOUND_MESSAGE);
            return modelAndView;
        }

        modelAndView = new ModelAndView(PRODUCT_PAGE);
        ProductDto product = productOpt.get();
        modelAndView.addObject(PRODUCT, product);
        modelAndView.addObject(PROPERTIES_MAP, getPropertiesMap(product));
        modelAndView.addObject(PRICES, productService.getProductPrices(product.getId()));
        return modelAndView;
    }

    @GetMapping("/products/{type}")
    public String viewProductsByType(@PathVariable("type") String typeStr,
                                     @RequestParam(value = "page", required = false) Integer selectedPage,
                                     Model model,
                                     Principal principal) {

        if (selectedPage == null || selectedPage < 1) {
            selectedPage = 1;
        }

        Integer finalSelectedPage = selectedPage;
        ProductTypeEnum.getByName(typeStr).ifPresent(t -> prepareModel(t, model, finalSelectedPage, principal));
        return PRODUCT_LIST_PAGE;
    }

    private void prepareModel(ProductTypeEnum type, Model model, Integer selectedPage, Principal principal) {
        long countOfProducts = productService.getProductsCountByType(type);
        model.addAttribute(SINGLE_PRODUCT_LIST_ATTR, countOfProducts == 1);
        int countOfPages = (int) Math.ceil(countOfProducts / (float) NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME);

        if (countOfPages > 0) {
            model.addAttribute(PRODUCT_AND_PRICE_ATTR, productService.getProductsInfoPageableByType(
                    type,
                    selectedPage - 1,
                    NUMBER_OF_PRODUCTS_PER_PAGE_AT_A_TIME,
                    principal)
            );
        }

        model.addAttribute(TYPE_ATTR, type.getName());
        LayoutUtils.preparePagination(model, selectedPage, countOfPages);
    }
}
