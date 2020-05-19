package priceobserver.data.wishproduct;

import priceobserver.data.product.Product;
import priceobserver.data.user.User;

import java.time.LocalDate;

public final class WishProductBuilder {
    private Long id;
    private LocalDate dateAdded;
    private Integer version;
    private Boolean isDeleted;
    private User user;
    private Product product;

    private WishProductBuilder() {
    }

    public static WishProductBuilder aWishProduct() {
        return new WishProductBuilder();
    }

    public WishProductBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WishProductBuilder withIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
        return this;
    }

    public WishProductBuilder withDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public WishProductBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public WishProductBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public WishProductBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public WishProduct build() {
        WishProduct wishProduct = new WishProduct();
        wishProduct.setId(id);
        wishProduct.setDateAdded(dateAdded);
        wishProduct.setVersion(version);
        wishProduct.setUser(user);
        wishProduct.setProduct(product);
        return wishProduct;
    }
}
