package priceobserver.dto.testutils;

import priceobserver.data.manufacturer.Manufacturer;
import priceobserver.data.manufacturer.ManufacturerBuilder;
import priceobserver.data.product.Product;
import priceobserver.data.product.ProductBuilder;
import priceobserver.data.productprice.ProductPrice;
import priceobserver.data.productprice.ProductPriceBuilder;
import priceobserver.data.productproperties.ProductProperties;
import priceobserver.data.productproperties.ProductPropertiesBuilder;
import priceobserver.data.producttype.ProductType;
import priceobserver.data.producttype.ProductTypeBuilder;
import priceobserver.data.store.Store;
import priceobserver.data.store.StoreBuilder;
import priceobserver.dto.manufacturer.ManufacturerDto;
import priceobserver.dto.manufacturer.ManufacturerDtoBuilder;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.product.ProductDtoBuilder;
import priceobserver.dto.productprice.ProductPriceDto;
import priceobserver.dto.productprice.ProductPriceDtoBuilder;
import priceobserver.dto.productproperties.ProductPropertiesDto;
import priceobserver.dto.productproperties.ProductPropertiesDtoBuilder;
import priceobserver.dto.producttype.ProductTypeDto;
import priceobserver.dto.producttype.ProductTypeDtoBuilder;
import priceobserver.dto.store.StoreDto;
import priceobserver.dto.store.StoreDtoBuilder;

import java.sql.Date;
import java.time.LocalDate;

public final class DtoTestPreparationHelper {

    // Product fields.
    private static final Long PRODUCT_ID = 12L;
    private static final Integer PRODUCT_VERSION = 2;
    private static final Integer PRODUCT_YEAR = 2004;
    private static final String PRODUCT_NAME = "Super-duper product name";
    private static final String PRODUCT_DESCRIPTION = "Super-duper product description";
    private static final String PRODUCT_MODEL = "Super-duper product model";
    private static final String PRODUCT_IMAGE_URL = "/super-duper/product/image";

    // Manufacturer fields.
    private static final Long MANUFACTURER_ID = 13L;
    private static final Integer MANUFACTURER_VERSION = 3;
    private static final String MANUFACTURER_NAME = "Manufacturer name";
    private static final String MANUFACTURER_COUNTRY = "Ukraine";

    // Product properties fields.
    private static final Long PRODUCT_PROPERTIES_ID = 14L;
    private static final String PRODUCT_PROPERTIES_JSON = "{\"color\": \"red\"}";
    private static final Integer PRODUCT_PROPERTIES_VERSION = 4;

    // Product type fields.
    private static final Long PRODUCT_TYPE_ID = 5L;
    private static final String PRODUCT_TYPE_NAME = "Product type name";

    // Store fields.
    private static final String STORE_NAME = "Store name";
    private static final Long STORE_ID = 1L;

    // Product price fields
    private static final Long PRODUCT_PRICE_ID = 45L;
    private static final Float PRODUCT_PRICE_VALUE = 123.50F;
    private static final LocalDate DATE_NOW = LocalDate.now();

    public static Product getPreparedProductEntity() {
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

    public static ProductDto getPreparedProductDto() {
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

    public static ManufacturerDto getPreparedManufacturerDto() {
        return ManufacturerDtoBuilder.aManufacturerDto()
                .withId(MANUFACTURER_ID)
                .withName(MANUFACTURER_NAME)
                .withCountry(MANUFACTURER_COUNTRY)
                .withVersion(MANUFACTURER_VERSION)
                .build();
    }

    public static ProductPropertiesDto getPreparedProductPropertiesDto() {
        return ProductPropertiesDtoBuilder.aProductPropertiesDto()
                .withId(PRODUCT_PROPERTIES_ID)
                .withProperties(PRODUCT_PROPERTIES_JSON)
                .withVersion(PRODUCT_PROPERTIES_VERSION)
                .build();
    }

    public static ProductTypeDto getPreparedProductTypeDto() {
        return ProductTypeDtoBuilder.aProductTypeDto()
                .withId(PRODUCT_TYPE_ID)
                .withName(PRODUCT_TYPE_NAME)
                .build();
    }

    public static Manufacturer getPreparedManufacturer() {
        return ManufacturerBuilder.aManufacturer()
                .withId(MANUFACTURER_ID)
                .withName(MANUFACTURER_NAME)
                .withCountry(MANUFACTURER_COUNTRY)
                .withVersion(MANUFACTURER_VERSION)
                .build();
    }

    public static ProductProperties getPreparedProductProperties() {
        return ProductPropertiesBuilder.aProductProperties()
                .withId(PRODUCT_PROPERTIES_ID)
                .withProperties(PRODUCT_PROPERTIES_JSON)
                .withVersion(PRODUCT_PROPERTIES_VERSION)
                .build();
    }

    public static ProductType getPreparedProductType() {
        return ProductTypeBuilder.aProductType()
                .withId(PRODUCT_TYPE_ID)
                .withName(PRODUCT_TYPE_NAME)
                .build();
    }

    public static Store getPreparedStoreEntity() {
        return StoreBuilder.aStore()
                .withId(STORE_ID)
                .withName(STORE_NAME)
                .build();
    }

    public static StoreDto getPreparedStoreDto() {
        return StoreDtoBuilder.aStoreDto()
                .withId(STORE_ID)
                .withName(STORE_NAME)
                .build();
    }

    public static ProductPrice getPreparedProductPriceEntity() {
        return ProductPriceBuilder.aProductPrice()
                .withId(PRODUCT_PRICE_ID)
                .withPrice(PRODUCT_PRICE_VALUE)
                .withDate(Date.valueOf(DATE_NOW))
                .withProduct(getPreparedProductEntity())
                .withStore(getPreparedStoreEntity())
                .build();
    }

    public static ProductPriceDto getPreparedProductPriceDto() {
        return ProductPriceDtoBuilder.aProductPriceDto()
                .withId(PRODUCT_PRICE_ID)
                .withPrice(PRODUCT_PRICE_VALUE)
                .withDate(DATE_NOW)
                .withProduct(getPreparedProductDto())
                .withStore(getPreparedStoreDto())
                .build();
    }
}
