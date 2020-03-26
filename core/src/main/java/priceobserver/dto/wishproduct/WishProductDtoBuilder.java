package priceobserver.dto.wishproduct;

import priceobserver.dto.product.*;
import priceobserver.dto.user.*;

import java.time.*;

public final class WishProductDtoBuilder {

    private Long id;
    private LocalDate dateAdded;
    private Integer version;
    private ProductDto product;
    private UserDto user;

    private WishProductDtoBuilder() {
    }

    public static WishProductDtoBuilder aWishProductDto() {
        return new WishProductDtoBuilder();
    }

    public WishProductDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public WishProductDtoBuilder withDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
        return this;
    }

    public WishProductDtoBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public WishProductDtoBuilder withProduct(ProductDto product) {
        this.product = product;
        return this;
    }

    public WishProductDtoBuilder withUser(UserDto user) {
        this.user = user;
        return this;
    }

    public WishProductDto build() {
        WishProductDto wishProductDto = new WishProductDto();
        wishProductDto.setId(id);
        wishProductDto.setDateAdded(dateAdded);
        wishProductDto.setVersion(version);
        wishProductDto.setProduct(product);
        wishProductDto.setUser(user);
        return wishProductDto;
    }
}