package priceobserver.data.product;

import priceobserver.data.manufacturer.Manufacturer;
import priceobserver.data.productproperties.ProductProperties;
import priceobserver.data.producttype.ProductType;

import java.time.Year;

public final class ProductBuilder {
    private Long id;
    private String name;
    private String description;
    private String model;
    private Year year;
    private String image;
    private Integer version;
    private ProductProperties productProperties;
    private ProductType productType;
    private Manufacturer manufacturer;

    private ProductBuilder() {
    }

    public static ProductBuilder aProduct() {
        return new ProductBuilder();
    }

    public ProductBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ProductBuilder withModel(String model) {
        this.model = model;
        return this;
    }

    public ProductBuilder withYear(Year year) {
        this.year = year;
        return this;
    }

    public ProductBuilder withImage(String image) {
        this.image = image;
        return this;
    }

    public ProductBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ProductBuilder withProductProperties(ProductProperties productProperties) {
        this.productProperties = productProperties;
        return this;
    }

    public ProductBuilder withProductType(ProductType productType) {
        this.productType = productType;
        return this;
    }

    public ProductBuilder withManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
        return this;
    }

    public Product build() {
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setDescription(description);
        product.setModel(model);
        product.setYear(year);
        product.setImage(image);
        product.setVersion(version);
        product.setProductProperties(productProperties);
        product.setProductType(productType);
        product.setManufacturer(manufacturer);
        return product;
    }
}
