package priceobserver.dto.wishlist;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.wishlist.WishList;
import priceobserver.dto.testutils.DtoTestPreparationHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WishListDtoConverterImplTest {

    private static WishList wishListEntity;
    private static WishListDto wishListDto;

    @Autowired
    private WishListDtoConverter wishListDtoConverter;

    @BeforeAll
    static void setUp() {
        wishListEntity = DtoTestPreparationHelper.getPreparedWishListEntity();
        wishListDto = DtoTestPreparationHelper.getPreparedWishListDto();
    }

    @Test
    void convertToDto() {
        assertEquals(wishListDto, wishListDtoConverter.convertToDto(wishListEntity));
    }

    @Test
    void convertToEntity() {
        assertEquals(wishListEntity, wishListDtoConverter.convertToEntity(wishListDto));
    }
}
