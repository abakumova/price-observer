package priceobserver.dto.productproperties;

public final class ProductPropertiesDtoBuilder {
    private Long id;
    private String properties;
    private String version;

    private ProductPropertiesDtoBuilder() {
    }

    public static ProductPropertiesDtoBuilder aProductPropertiesDto() {
        return new ProductPropertiesDtoBuilder();
    }

    public ProductPropertiesDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductPropertiesDtoBuilder withProperties(String properties) {
        this.properties = properties;
        return this;
    }

    public ProductPropertiesDtoBuilder withVersion(String version) {
        this.version = version;
        return this;
    }

    public ProductPropertiesDto build() {
        ProductPropertiesDto productPropertiesDto = new ProductPropertiesDto();
        productPropertiesDto.setId(id);
        productPropertiesDto.setProperties(properties);
        productPropertiesDto.setVersion(version);
        return productPropertiesDto;
    }
}
