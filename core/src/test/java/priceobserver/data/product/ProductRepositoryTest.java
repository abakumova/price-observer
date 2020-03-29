package priceobserver.data.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import priceobserver.data.manufacturer.Manufacturer;
import priceobserver.data.manufacturer.ManufacturerRepository;
import priceobserver.data.productproperties.ProductProperties;
import priceobserver.data.productproperties.ProductPropertiesRepository;
import priceobserver.data.producttype.ProductType;
import priceobserver.data.producttype.ProductTypeRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedManufacturerEntity;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductEntity;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductPropertiesEntity;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedProductTypeEntity;

@DataJpaTest
class ProductRepositoryTest {

    private static final String PRODUCT_NAME_1 = "Product 1";
    private static final String PRODUCT_NAME_2 = "Product 2";
    private static final String PRODUCT_NAME_3 = "Product 3";
    private static final String UPDATED_NAME = "Updated_name";

    private static Product product1;
    private static Product product2;
    private static Product product3;

    private static Manufacturer manufacturer;
    private static ProductProperties productProperties;
    private static ProductType productType;
    private static List<Product> products;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ManufacturerRepository manufacturerRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;
    @Autowired
    private ProductPropertiesRepository productPropertiesRepository;


    @BeforeEach
    void setUp() {
        manufacturer = getPreparedManufacturerEntity();
        productProperties = getPreparedProductPropertiesEntity();
        productType = getPreparedProductTypeEntity();

        product1 = getPreparedProductEntity();
        product2 = getPreparedProductEntity();
        product3 = getPreparedProductEntity();
        product1.setName(PRODUCT_NAME_1);
        product2.setName(PRODUCT_NAME_2);
        product3.setName(PRODUCT_NAME_3);
        products = Arrays.asList(product1, product2, product3);

        manufacturerRepository.save(manufacturer);
        productTypeRepository.save(productType);
        productPropertiesRepository.save(productProperties);

        List<Product> products = Arrays.asList(product1, product2, product3);
        Optional<Manufacturer> persistedManufacturer = manufacturerRepository.findFirstByName(manufacturer.getName());
        Optional<ProductProperties> persistedProductProperties = productPropertiesRepository
                .findFirstByProperties(productProperties.getProperties());
        Optional<ProductType> persistedProductType = productTypeRepository.findFirstByName(productType.getName());

        if (persistedManufacturer.isPresent()
                && persistedProductProperties.isPresent()
                && persistedProductType.isPresent()) {

            for (Product product : products) {
                product.setManufacturer(persistedManufacturer.get());
                product.setProductType(persistedProductType.get());
                product.setProductProperties(persistedProductProperties.get());
            }
        } else {
            throw new RuntimeException();
        }
    }

    @Test
    void shouldSaveListOfProductsIntoDb() {
        productRepository.saveAll(products);

        assertEquals(3, productRepository.count());

        List<Product> persistedProducts = (List<Product>) productRepository.findAll();
        assertTrue(persistedProducts.stream().anyMatch(p -> p.getName().equals(PRODUCT_NAME_1)));
        assertTrue(persistedProducts.stream().anyMatch(p -> p.getName().equals(PRODUCT_NAME_2)));
        assertTrue(persistedProducts.stream().anyMatch(p -> p.getName().equals(PRODUCT_NAME_3)));
    }

    @Test
    void shouldRemoveProduct() {
        productRepository.saveAll(products);

        assertEquals(products.size(), productRepository.count());

        Optional<Product> product = productRepository.findFirstByName(PRODUCT_NAME_2);
        if (!product.isPresent()) {
            throw new RuntimeException();
        }

        productRepository.delete(product.get());
        assertEquals(products.size() - 1, productRepository.count());
    }

    @Test
    void shouldUpdateProduct() {
        Product persistedProduct = productRepository.save(product1);

        assertEquals(1, productRepository.count());

        Long id = persistedProduct.getId();
        persistedProduct.setName(UPDATED_NAME);

        persistedProduct = productRepository.save(persistedProduct);

        assertEquals(UPDATED_NAME, persistedProduct.getName());
    }
}