package priceobserver.dto.productprice;

import priceobserver.dto.product.ProductDto;
import priceobserver.dto.store.StoreDto;

import java.time.LocalDate;

public final class ProductPriceDtoBuilder {
    private Long id;
    private Float price;
    private LocalDate date;
    private ProductDto product;
    private StoreDto store;

    private ProductPriceDtoBuilder() {
    }

    public static ProductPriceDtoBuilder aProductPriceDto() {
        return new ProductPriceDtoBuilder();
    }

    public ProductPriceDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductPriceDtoBuilder withPrice(Float price) {
        this.price = price;
        return this;
    }

    public ProductPriceDtoBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ProductPriceDtoBuilder withProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public ProductPriceDtoBuilder withStore(StoreDto store) {
        this.store = store;
        return this;
    }

    public ProductPriceDto build() {
        ProductPriceDto productPriceDto = new ProductPriceDto();
        productPriceDto.setId(id);
        productPriceDto.setPrice(price);
        productPriceDto.setDate(date);
        productPriceDto.setProduct(product);
        productPriceDto.setStore(store);
        return productPriceDto;
    }
}
