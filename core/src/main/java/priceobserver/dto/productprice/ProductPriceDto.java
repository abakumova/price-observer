package priceobserver.dto.productprice;

import priceobserver.dto.product.ProductDto;
import priceobserver.dto.store.StoreDto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ProductPriceDto implements Serializable {

    private Long id;
    private Float price;
    private LocalDate date;
    private ProductDto product;
    private StoreDto store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public StoreDto getStore() {
        return store;
    }

    public void setStore(StoreDto store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "ProductPriceDto{" +
                "id=" + id +
                ", price=" + price +
                ", date=" + date +
                ", product=" + product +
                ", store=" + store +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductPriceDto that = (ProductPriceDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(price, that.price) &&
                Objects.equals(date, that.date) &&
                Objects.equals(product, that.product) &&
                Objects.equals(store, that.store);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, date, product, store);
    }
}
