package priceobserver.dto.product;

import org.springframework.stereotype.Component;
import priceobserver.data.product.Product;

@Component
public class ProductDtoConverterImpl implements ProductDtoConverter {
    @Override
    public ProductDto convertToDto(Product entity) {
        return null;
    }

    @Override
    public Product convertToEntity(ProductDto dto) {
        return null;
    }
}
