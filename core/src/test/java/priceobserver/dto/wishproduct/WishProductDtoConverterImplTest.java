package priceobserver.dto.wishproduct;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import priceobserver.data.wishproduct.WishProduct;
import priceobserver.dto.DtoTestConfiguration;
import priceobserver.dto.testutils.DtoTestPreparationHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = DtoTestConfiguration.class)
class WishProductDtoConverterImplTest {

    private static WishProduct wishProductEntity;
    private static WishProductDto wishProductDto;

    @Autowired
    private WishProductDtoConverter wishProductDtoConverter;

    @BeforeAll
    static void setUp() {
        wishProductEntity = DtoTestPreparationHelper.getPreparedWishProductEntity();
        wishProductDto = DtoTestPreparationHelper.getPreparedWishProductDto();
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