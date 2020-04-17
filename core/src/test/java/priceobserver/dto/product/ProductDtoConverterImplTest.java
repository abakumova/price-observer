package priceobserver.dto.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import priceobserver.data.product.Product;
import priceobserver.dto.DtoTestConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductDto;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductEntity;

@SpringBootTest
@ContextConfiguration(classes = DtoTestConfiguration.class)
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
        assertEquals(productDto, productDtoConverter.convertToDto(productEntity));
    }

    @Test
    void shouldConvertToEntity() {
        assertEquals(productEntity, productDtoConverter.convertToEntity(productDto));
    }
}
