package priceobserver.dto.wishproduct;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.wishproduct.WishProduct;
import priceobserver.dto.testutils.DtoTestPreparationHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WishProductDtoConverterImplTest {

    private static WishProduct wishProductEntity;
    private static WishProductDto wishProductDto;

    @Autowired
    private WishProductDtoConverter wishProductDtoConverter;

    @BeforeAll
    static void setUp() {
        wishProductEntity = DtoTestPreparationHelper.getPreparedWishListEntity();
        wishProductDto = DtoTestPreparationHelper.getPreparedWishListDto();
    }

    @Test
    void convertToDto() {
        assertEquals(wishProductDto, wishProductDtoConverter.convertToDto(wishProductEntity));
    }

    @Test
    void convertToEntity() {
        assertEquals(wishProductEntity, wishProductDtoConverter.convertToEntity(wishProductDto));
    }
}