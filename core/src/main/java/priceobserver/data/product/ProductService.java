package priceobserver.data.product;

import priceobserver.dto.product.ProductDto;
import priceobserver.dto.producttype.ProductTypeEnum;

import java.util.List;

public interface ProductService {
    List<ProductDto> getProductsByType(ProductTypeEnum type);
}
