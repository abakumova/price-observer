package priceobserver.dto.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.product.Product;

@SpringBootTest
class ProductDtoConverterImplTest {

    private static ProductDto productDto;
    private static Product productEntity;

    @Autowired
    private ProductDtoConverter productDtoConverter;

    @BeforeAll
    static void setUp() {
        //TODO finish me lazy
    }

    @Test
    void shouldConvertToDto() {
    }

    @Test
    void shouldConvertToEntity() {
    }
}