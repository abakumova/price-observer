package priceobserver.dto.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.product.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductDto;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductEntity;

@SpringBootTest
class ProductDtoConverterImplTest {

    private static ProductDto productDto;
    private static Product productEntity;

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @BeforeAll
    static void setUp() {
        productDto = getPreparedProductDto();
        productEntity = getPreparedProductEntity();
    }

    @Test
    void shouldConvertToDto() {
        ProductDto entityConvertedToDto = productDtoConverter.convertToDto(productEntity);
        assertEquals(productDto, entityConvertedToDto);
    }

    @Test
    void shouldConvertToEntity() {
        Product dtoConvertedToEntity = productDtoConverter.convertToEntity(productDto);
        assertEquals(productEntity, dtoConvertedToEntity);
    }
}