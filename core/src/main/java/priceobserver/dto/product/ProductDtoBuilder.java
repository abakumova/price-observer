package priceobserver.dto.product;

import priceobserver.dto.manufacturer.ManufacturerDto;
import priceobserver.dto.productproperties.ProductPropertiesDto;
import priceobserver.dto.producttype.ProductTypeDto;

public final class ProductDtoBuilder {
    private Long id;
    private String name;
    private String description;
    private String model;
    private Integer year;
    private String image;
    private Integer version;
    private ProductPropertiesDto productProperties;
    private ProductTypeDto productType;
    private ManufacturerDto manufacturer;

    private ProductDtoBuilder() {
    }

    public static ProductDtoBuilder aProductDto() {
        return new ProductDtoBuilder();
    }

    public ProductDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductDtoBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductDtoBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductDtoBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public ProductDtoBuilder withYear(Integer year) {
        this.year = year;
        return this;
    }

    public ProductDtoBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public ProductDtoBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ProductDtoBuilder withProductProperties(ProductPropertiesDto productProperties) {
        this.productProperties = productProperties;
        return this;
    }

    public ProductDtoBuilder withProductType(ProductTypeDto productType) {
        this.productType = productType;
        return this;
    }

    public ProductDtoBuilder withManufacturer(ManufacturerDto manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public ProductDto build() {
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName(name);
        productDto.setDescription(description);
        productDto.setModel(model);
        productDto.setYear(year);
        productDto.setImage(image);
        productDto.setVersion(version);
        productDto.setProductProperties(productProperties);
        productDto.setProductType(productType);
        productDto.setManufacturer(manufacturer);
        return productDto;
    }
}
