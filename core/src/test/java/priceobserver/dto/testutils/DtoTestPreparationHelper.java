package priceobserver.dto.testutils;

import priceobserver.data.manufacturer.*;
import priceobserver.data.product.*;
import priceobserver.data.productprice.*;
import priceobserver.data.productproperties.*;
import priceobserver.data.producttype.*;
import priceobserver.data.store.*;
import priceobserver.data.user.*;
import priceobserver.data.userrole.*;
import priceobserver.data.wishproduct.*;
import priceobserver.dto.manufacturer.*;
import priceobserver.dto.product.*;
import priceobserver.dto.productprice.*;
import priceobserver.dto.productproperties.*;
import priceobserver.dto.producttype.*;
import priceobserver.dto.store.*;
import priceobserver.dto.user.*;
import priceobserver.dto.wishproduct.*;

import java.sql.*;
import java.time.*;

public final class DtoTestPreparationHelper {

    //General fields
    private static final LocalDate DATE_NOW = LocalDate.now();

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

    // Wish product fields
    private static final Long WISH_PRODUCT_ID = 50L;
    private static final Integer WISH_PRODUCT_VERSION = 11;

    //User fields
    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "First name";
    private static final String LAST_NAME = "Last name";
    private static final String EMAIL = "email@email.email";
    private static final String ENCRYPTED_PASSWORD = "Encrypted password";
    private static final String PASSWORD = "Password";
    private static final String USER_ROLE_AS_STRING = "user";
    private static final LocalDate BIRTH = LocalDate.now();
    private static final Integer VERSION = 1;
    private static final UserRole USER_ROLE = new UserRole();
    private static final Byte ROLE_ID = (byte) 2;

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

    public static WishProductDto getPreparedWishProductDto() {
        return WishProductDtoBuilder.aWishProductDto()
                .withId(WISH_PRODUCT_ID)
                .withVersion(WISH_PRODUCT_VERSION)
                .withDateAdded(DATE_NOW)
                .withProduct(getPreparedProductDto())
                .withUser(getPreparedUserDto())
                .build();
    }

    public static WishProduct getPreparedWishProductEntity() {
        return WishProductBuilder.aWishProduct()
                .withId(WISH_PRODUCT_ID)
                .withVersion(WISH_PRODUCT_VERSION)
                .withDateAdded(Date.valueOf(DATE_NOW))
                .withProduct(getPreparedProductEntity())
                .withUser(getPreparedUserEntity())
                .build();
    }

    public static UserDto getPreparedUserDto() {
        return UserDtoBuilder.anUserDto()
                .withId(USER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withEncryptedPassword(ENCRYPTED_PASSWORD)
                .withPassword(PASSWORD)
                .withBirth(BIRTH)
                .withVersion(VERSION)
                .withUserRole(USER_ROLE_AS_STRING)
                .build();
    }

    public static User getPreparedUserEntity() {
        USER_ROLE.setName(USER_ROLE_AS_STRING);
        USER_ROLE.setId(ROLE_ID);

        return UserBuilder.anUser()
                .withId(USER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withEncryptedPassword(ENCRYPTED_PASSWORD)
                .withPassword(PASSWORD)
                .withBirth(Date.valueOf(BIRTH))
                .withVersion(VERSION)
                .withUserRole(USER_ROLE)
                .build();
    }
}