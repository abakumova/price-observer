package priceobserver.data.productprice;

import priceobserver.data.product.Product;
import priceobserver.data.store.Store;

import java.time.LocalDate;

public final class ProductPriceBuilder {
    private Long id;
    private Float price;
    private LocalDate date;
    private Product product;
    private Store store;

    private ProductPriceBuilder() {
    }

    public static ProductPriceBuilder aProductPrice() {
        return new ProductPriceBuilder();
    }

    public ProductPriceBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ProductPriceBuilder withPrice(Float price) {
        this.price = price;
        return this;
    }

    public ProductPriceBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ProductPriceBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public ProductPriceBuilder withStore(Store store) {
        this.store = store;
        return this;
    }

    public ProductPrice build() {
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(id);
        productPrice.setPrice(price);
        productPrice.setDate(date);
        productPrice.setProduct(product);
        productPrice.setStore(store);
        return productPrice;
    }
}
