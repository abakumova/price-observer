package priceobserver.data.producttype;

public final class ProductTypeBuilder {
    private Long id;
    private String name;

    private ProductTypeBuilder() {
    }

    public static ProductTypeBuilder aProductType() {
        return new ProductTypeBuilder();
    }

    public ProductTypeBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductTypeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProductType build() {
        ProductType productType = new ProductType();
        productType.setId(id);
        productType.setName(name);
        return productType;
    }
}
