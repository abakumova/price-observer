package priceobserver;

import priceobserver.dto.product.ProductDto;

import java.util.List;

public interface ParsingManager {

    List<ProductDto> parsePages();

    void loadProducts(List<ProductDto> products);
}
