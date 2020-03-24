package priceobserver.data.wishlist;

import priceobserver.data.product.Product;
import priceobserver.data.user.User;

import java.sql.Date;

public final class WishListBuilder {
    private Long id;
    private Date dateAdded;
    private Integer version;
    private User user;
    private Product product;

    private WishListBuilder() {
    }

    public static WishListBuilder aWishList() {
        return new WishListBuilder();
    }

    public WishListBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WishListBuilder withDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public WishListBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public WishListBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public WishListBuilder withProduct(Product product) {
        this.product = product;
        return this;
    }

    public WishList build() {
        WishList wishList = new WishList();
        wishList.setId(id);
        wishList.setDateAdded(dateAdded);
        wishList.setVersion(version);
        wishList.setUser(user);
        wishList.setProduct(product);
        return wishList;
    }
}
