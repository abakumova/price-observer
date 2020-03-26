package priceobserver.dto.wishlist;

import priceobserver.dto.product.ProductDto;
import priceobserver.dto.user.UserDto;

import java.time.LocalDate;

public final class WishListDtoBuilder {
    private Long id;
    private LocalDate dateAdded;
    private Integer version;
    private ProductDto product;
    private UserDto user;

    private WishListDtoBuilder() {
    }

    public static WishListDtoBuilder aWishListDto() {
        return new WishListDtoBuilder();
    }

    public WishListDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WishListDtoBuilder withDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public WishListDtoBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public WishListDtoBuilder withProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public WishListDtoBuilder withUser(UserDto user) {
        this.user = user;
        return this;
    }

    public WishListDto build() {
        WishListDto wishListDto = new WishListDto();
        wishListDto.setId(id);
        wishListDto.setDateAdded(dateAdded);
        wishListDto.setVersion(version);
        wishListDto.setProduct(product);
        wishListDto.setUser(user);
        return wishListDto;
    }
}
