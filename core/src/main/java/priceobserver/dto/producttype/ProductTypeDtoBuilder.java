package priceobserver.dto.producttype;

public final class ProductTypeDtoBuilder {
    private Long id;
    private String name;

    private ProductTypeDtoBuilder() {
    }

    public static ProductTypeDtoBuilder aProductTypeDto() {
        return new ProductTypeDtoBuilder();
    }

    public ProductTypeDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductTypeDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductTypeDto build() {
        ProductTypeDto productTypeDto = new ProductTypeDto();
        productTypeDto.setId(id);
        productTypeDto.setName(name);
        return productTypeDto;
    }
}
