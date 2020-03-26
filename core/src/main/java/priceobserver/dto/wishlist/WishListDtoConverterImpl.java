package priceobserver.dto.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priceobserver.data.wishlist.WishList;
import priceobserver.data.wishlist.WishListBuilder;
import priceobserver.dto.product.ProductDtoConverter;
import priceobserver.dto.user.UserDtoConverter;

import java.sql.Date;

@Component
public class WishListDtoConverterImpl implements WishListDtoConverter {

    private UserDtoConverter userDtoConverter;
    private ProductDtoConverter productDtoConverter;

    @Autowired
    public WishListDtoConverterImpl(UserDtoConverter userDtoConverter, ProductDtoConverter productDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.productDtoConverter = productDtoConverter;
    }

    @Override
    public WishListDto convertToDto(WishList entity) {
        return WishListDtoBuilder.aWishListDto()
                .withId(entity.getId())
                .withDateAdded(entity.getDateAdded().toLocalDate())
                .withVersion(entity.getVersion())
                .withUser(userDtoConverter.convertToDto(entity.getUser()))
                .withProduct(productDtoConverter.convertToDto(entity.getProduct()))
                .build();
    }

    @Override
    public WishList convertToEntity(WishListDto dto) {
        return WishListBuilder.aWishList()
                .withId(dto.getId())
                .withDateAdded(Date.valueOf(dto.getDateAdded()))
                .withVersion(dto.getVersion())
                .withUser(userDtoConverter.convertToEntity(dto.getUser()))
                .withProduct(productDtoConverter.convertToEntity(dto.getProduct()))
                .build();
    }
}
