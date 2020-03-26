package priceobserver.dto.productprice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.productprice.ProductPrice;

import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductPriceDto;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductPriceEntity;

@SpringBootTest
class ProductPriceDtoConverterImplTest {

    private static ProductPriceDto productPriceDto;
    private static ProductPrice productPriceEntity;

    @Autowired
    private ProductPriceDtoConverter productPriceDtoConverter;

    @BeforeAll
    static void setUp() {
        productPriceDto = getPreparedProductPriceDto();
        productPriceEntity = getPreparedProductPriceEntity();
    }

    @Test
    void convertToDto() {
        Assertions.assertEquals(productPriceDto, productPriceDtoConverter.convertToDto(productPriceEntity));
    }

    @Test
    void convertToEntity() {
        Assertions.assertEquals(productPriceEntity, productPriceDtoConverter.convertToEntity(productPriceDto));
    }
}
