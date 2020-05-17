package priceobserver.data.product;

import priceobserver.dto.product.ProductDto;
import priceobserver.dto.producttype.ProductTypeEnum;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDto> getProductsByType(ProductTypeEnum type);
    Optional<ProductDto> getOneById(Long id);
    long getProductsCountByType(ProductTypeEnum type);
    long getProductsByNameOrModelContaining(String contains);
    List<ProductAndPricePresentation> getProductsByNameOrModelContaining(String contains,
                                                                         int pageNumber,
                                                                         int rowsOnPageCount);
    List<ProductAndPricePresentation> getProductsInfoPageableByType(ProductTypeEnum type,
                                                                    int pageNumber,
                                                                    int rowsOnPageCount);
}
