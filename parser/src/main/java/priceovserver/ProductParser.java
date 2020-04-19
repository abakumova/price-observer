package priceovserver;

import priceobserver.dto.product.ProductDto;

import java.util.List;

public interface ProductParser {

    List<ProductDto> parse(String avicUrlWithProduct);
}
