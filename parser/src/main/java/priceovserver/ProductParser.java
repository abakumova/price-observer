package priceovserver;

import priceobserver.dto.product.ProductDto;

import java.util.List;

public interface ProductParser {

    /**
     * Get a list of Products by given URL to the products page.
     * For example, URL may look like "https://avic.ua/iphone.html".
     *
     * @param avicUrlWithProduct URL to products page
     * @return list of products
     */
    List<ProductDto> parse(String avicUrlWithProduct);
}
