package priceobserver.data.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import priceobserver.data.productprice.ProductPriceRepository;
import priceobserver.data.productprice.ShortProductPriceProjection;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.product.ProductDtoConverter;
import priceobserver.dto.producttype.ProductTypeEnum;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDtoConverter productConverter;
    private final ProductPriceRepository priceRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ProductDtoConverter productConverter, ProductPriceRepository priceRepository) {
        this.productRepository = productRepository;
        this.productConverter = productConverter;
        this.priceRepository = priceRepository;
    }

    @Override
    public List<ProductDto> getProductsByType(ProductTypeEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("ProductTypeEnum must not be null!");
        }
        List<ProductDto> products = productRepository.findAllByProductTypeId(type.getId()).stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
        products.forEach(this::prepareImageUrl);
        return products;
    }

    @Override
    public Optional<ProductDto> getOneById(Long id) {
        if (id == null || id < 1) {
            throw new IllegalArgumentException("Id can't be null or less than 1!");
        }
        Optional<ProductDto> product = productRepository.findById(id).map(productConverter::convertToDto);
        product.ifPresent(this::prepareImageUrl);
        return product;
    }

    @Override
    public long getProductCountByType(ProductTypeEnum type) {
        if (type == null) {
            throw new IllegalArgumentException("Type can't be null!");
        }
        return productRepository.countAllByProductTypeId(type.getId());
    }

    @Override
    public List<ProductAndPricePresentation> getProductsByNameOrModelContaining(String contains,
                                                                                int pageNumber,
                                                                                int rowsOnPageCount) {
        if (pageNumber < 0 || rowsOnPageCount < 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid page number or rows on page count. Page number = %d, rowsOnPageCount = %d",
                            pageNumber, rowsOnPageCount)
            );
        } else if (contains == null || contains.isBlank()) {
            throw new IllegalArgumentException("String with contains can't be null or blank!");
        }
        List<ProductDto> products = productRepository.findAllByNameContainingOrModelContaining(
                contains,
                contains,
                PageRequest.of(pageNumber, rowsOnPageCount)
        ).stream().map(productConverter::convertToDto).collect(Collectors.toList());
        prepareImageUrl(products);
        return getProductAndPricePresentationList(products);
    }

    @Override
    public List<ProductAndPricePresentation> getProductsInfoPageableByType(ProductTypeEnum type, int pageNumber, int rowsOnPageCount) {
        if (pageNumber < 0 || rowsOnPageCount < 1) {
            throw new IllegalArgumentException(
                    String.format("Invalid page number or rows on page count. Page number = %d, rowsOnPageCount = %d",
                            pageNumber, rowsOnPageCount)
            );
        } else if (type == null) {
            throw new IllegalArgumentException("Type can't be null!");
        }

        List<ProductDto> products = productRepository.findAllByProductTypeId(type.getId(),
                PageRequest.of(pageNumber, rowsOnPageCount)).stream()
                .map(productConverter::convertToDto)
                .collect(Collectors.toList());
        prepareImageUrl(products);
        return getProductAndPricePresentationList(products);
    }

    private void prepareImageUrl(List<ProductDto> products) {
        products.forEach(this::prepareImageUrl);
    }

    private void prepareImageUrl(ProductDto p) {
        p.setImage(p.getImage().replace("webapp/src/main/resources/static", ""));
    }

    private ProductAndPricePresentation getPresentation(ProductDto product, List<ShortProductPriceProjection> prices) {
        return new ProductAndPricePresentation(product, prices.stream()
                .filter(pr -> Objects.equals(pr.getProductId(), product.getId()))
                .findFirst()
                .orElse(null));
    }

    private List<ProductAndPricePresentation> getProductAndPricePresentationList(List<ProductDto> products) {
        List<ShortProductPriceProjection> prices = priceRepository.findFreshPricesForProductsWithIds(
                products.stream()
                        .map(ProductDto::getId)
                        .collect(Collectors.toList())
        );
        return products.stream().map(p -> getPresentation(p, prices)).collect(Collectors.toList());
    }
}
