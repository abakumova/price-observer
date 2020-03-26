package priceobserver.dto.wishproduct;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import priceobserver.data.wishproduct.*;
import priceobserver.dto.testutils.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
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