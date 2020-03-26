package priceobserver.data.productproperties;

public final class ProductPropertiesBuilder {
    private Long id;
    private String properties;
    private Integer version;

    private ProductPropertiesBuilder() {
    }

    public static ProductPropertiesBuilder aProductProperties() {
        return new ProductPropertiesBuilder();
    }

    public ProductPropertiesBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductPropertiesBuilder withProperties(String properties) {
        this.properties = properties;
        return this;
    }

    public ProductPropertiesBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public ProductProperties build() {
        ProductProperties productProperties = new ProductProperties();
        productProperties.setId(id);
        productProperties.setProperties(properties);
        productProperties.setVersion(version);
        return productProperties;
    }
}
