package priceobserver.dto.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.manufacturer.Manufacturer;
import priceobserver.data.manufacturer.ManufacturerBuilder;
import priceobserver.data.product.Product;
import priceobserver.data.product.ProductBuilder;
import priceobserver.data.productproperties.ProductProperties;
import priceobserver.data.productproperties.ProductPropertiesBuilder;
import priceobserver.data.producttype.ProductType;
import priceobserver.data.producttype.ProductTypeBuilder;
import priceobserver.dto.manufacturer.ManufacturerDto;
import priceobserver.dto.manufacturer.ManufacturerDtoBuilder;
import priceobserver.dto.productproperties.ProductPropertiesDto;
import priceobserver.dto.productproperties.ProductPropertiesDtoBuilder;
import priceobserver.dto.producttype.ProductTypeDto;
import priceobserver.dto.producttype.ProductTypeDtoBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductDtoConverterImplTest {

    private static final Long PRODUCT_ID = 12L;
    private static final Integer PRODUCT_VERSION = 2;
    private static final Integer PRODUCT_YEAR = 2004;
    private static final String PRODUCT_NAME = "Super-duper product name";
    private static final String PRODUCT_DESCRIPTION = "Super-duper product description";
    private static final String PRODUCT_MODEL = "Super-duper product model";
    private static final String PRODUCT_IMAGE_URL = "/super-duper/product/image";

    private static final Long MANUFACTURER_ID = 13L;
    private static final Integer MANUFACTURER_VERSION = 3;
    private static final String MANUFACTURER_NAME = "Manufacturer name";
    private static final String MANUFACTURER_COUNTRY = "Ukraine";

    private static final Long PRODUCT_PROPERTIES_ID = 14L;
    private static final String PRODUCT_PROPERTIES_JSON = "{\"color\": \"red\"}";
    private static final Integer PRODUCT_PROPERTIES_VERSION = 4;

    private static final Long PRODUCT_TYPE_ID = 5L;
    private static final String PRODUCT_TYPE_NAME = "Product type name";

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

    private static Product getPreparedProductEntity() {
        return ProductBuilder.aProduct()
                .withId(PRODUCT_ID)
                .withName(PRODUCT_NAME)
                .withDescription(PRODUCT_DESCRIPTION)
                .withVersion(PRODUCT_VERSION)
                .withYear(PRODUCT_YEAR)
                .withModel(PRODUCT_MODEL)
                .withImage(PRODUCT_IMAGE_URL)
                .withManufacturer(getPreparedManufacturer())
                .withProductProperties(getPreparedProductProperties())
                .withProductType(getPreparedProductType())
                .build();
    }

    private static ProductDto getPreparedProductDto() {
        return ProductDtoBuilder.aProductDto()
                .withId(PRODUCT_ID)
                .withName(PRODUCT_NAME)
                .withDescription(PRODUCT_DESCRIPTION)
                .withVersion(PRODUCT_VERSION)
                .withYear(PRODUCT_YEAR)
                .withModel(PRODUCT_MODEL)
                .withImage(PRODUCT_IMAGE_URL)
                .withManufacturer(getPreparedManufacturerDto())
                .withProductProperties(getPreparedProductPropertiesDto())
                .withProductType(getPreparedProductTypeDto())
                .build();
    }

    private static ManufacturerDto getPreparedManufacturerDto() {
        return ManufacturerDtoBuilder.aManufacturerDto()
                .withId(MANUFACTURER_ID)
                .withName(MANUFACTURER_NAME)
                .withCountry(MANUFACTURER_COUNTRY)
                .withVersion(MANUFACTURER_VERSION)
                .build();
    }

    private static ProductPropertiesDto getPreparedProductPropertiesDto() {
        return ProductPropertiesDtoBuilder.aProductPropertiesDto()
                .withId(PRODUCT_PROPERTIES_ID)
                .withProperties(PRODUCT_PROPERTIES_JSON)
                .withVersion(PRODUCT_PROPERTIES_VERSION)
                .build();
    }

    private static ProductTypeDto getPreparedProductTypeDto() {
        return ProductTypeDtoBuilder.aProductTypeDto()
                .withId(PRODUCT_TYPE_ID)
                .withName(PRODUCT_TYPE_NAME)
                .build();
    }

    private static Manufacturer getPreparedManufacturer() {
        return ManufacturerBuilder.aManufacturer()
                .withId(MANUFACTURER_ID)
                .withName(MANUFACTURER_NAME)
                .withCountry(MANUFACTURER_COUNTRY)
                .withVersion(MANUFACTURER_VERSION)
                .build();
    }

    private static ProductProperties getPreparedProductProperties() {
        return ProductPropertiesBuilder.aProductProperties()
                .withId(PRODUCT_PROPERTIES_ID)
                .withProperties(PRODUCT_PROPERTIES_JSON)
                .withVersion(PRODUCT_PROPERTIES_VERSION)
                .build();
    }

    private static ProductType getPreparedProductType() {
        return ProductTypeBuilder.aProductType()
                .withId(PRODUCT_TYPE_ID)
                .withName(PRODUCT_TYPE_NAME)
                .build();
    }
}