package priceobserver.data.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.product.ProductDtoConverter;
import priceobserver.dto.producttype.ProductTypeEnum;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductDtoConverter converter;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductDtoConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public List<ProductDto> getProductsByType(ProductTypeEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("ProductTypeEnum must not be null!");
        }
        List<ProductDto> products = repository.findAllByProductTypeId(type.getId()).stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
        products.forEach(this::prepareImageUrl);
        return products;
    }

    private void prepareImageUrl(ProductDto p) {
        p.setImage(p.getImage().replace("webapp/src/main/resources/static", ""));
    }
}
