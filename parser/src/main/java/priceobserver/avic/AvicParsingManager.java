package priceobserver.avic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import priceobserver.ParsingManager;
import priceobserver.ProductParser;
import priceobserver.data.manufacturer.Manufacturer;
import priceobserver.data.manufacturer.ManufacturerEnum;
import priceobserver.data.manufacturer.ManufacturerRepository;
import priceobserver.data.product.Product;
import priceobserver.data.product.ProductRepository;
import priceobserver.data.producttype.ProductType;
import priceobserver.data.producttype.ProductTypeRepository;
import priceobserver.dto.producttype.ProductTypeEnum;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static priceobserver.data.manufacturer.ManufacturerEnum.APPLE;
import static priceobserver.dto.producttype.ProductTypeEnum.ALL_IN_ONE;
import static priceobserver.dto.producttype.ProductTypeEnum.LAPTOP;
import static priceobserver.dto.producttype.ProductTypeEnum.SMARTPHONE;
import static priceobserver.dto.producttype.ProductTypeEnum.SMARTWATCH;
import static priceobserver.dto.producttype.ProductTypeEnum.TABLET;

@Component
public class AvicParsingManager implements ParsingManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvicParsingManager.class);

    private static final String PAGE_WITH_MACBOOKS = "https://avic.ua/macbook";
    private static final String PAGE_WITH_IPHONES = "https://avic.ua/iphone";
    private static final String PAGE_WITH_IPADS = "https://avic.ua/ipad";
    private static final String PAGE_WITH_IMACS = "https://avic.ua/imac";
    private static final String PAGE_WITH_APPLE_WATCH = "https://avic.ua/apple-watch-umnie-chasi";

    private static final Map<String, Pair<ManufacturerEnum, ProductTypeEnum>> payload = Map.of(
        PAGE_WITH_APPLE_WATCH, Pair.of(APPLE, SMARTWATCH),
        PAGE_WITH_IMACS, Pair.of(APPLE, ALL_IN_ONE),
        PAGE_WITH_IPADS, Pair.of(APPLE, TABLET),
        PAGE_WITH_IPHONES, Pair.of(APPLE, SMARTPHONE),
        PAGE_WITH_MACBOOKS, Pair.of(APPLE, LAPTOP)
    );

    private final ProductParser parser;
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public AvicParsingManager(ProductParser parser,
                              ProductRepository productRepository,
                              ManufacturerRepository manufacturerRepository,
                              ProductTypeRepository productTypeRepository) {
        this.parser = parser;
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Transactional
    @Override
    public void run() {
        LOGGER.info("Started parsing Avic products. Payload: {}", payload);
        payload.forEach(this::processProducts);
        LOGGER.info("Avic parsing was successfully finished.");
    }

    private void processProducts(String key, Pair<ManufacturerEnum, ProductTypeEnum> value) {
        loadProducts(parser.parse(key), value.getFirst(), value.getSecond());
    }

    @Override
    public void loadProducts(List<Product> products,
                             ManufacturerEnum manufacturer,
                             ProductTypeEnum productType) {
        LOGGER.info("Saving products with type {} and manufacturer {} to DB. List size {}",
                productType.getName(), manufacturer.getName(), products.size());

        Optional<Manufacturer> manufacturerOpt = manufacturerRepository.findById(manufacturer.getId());
        Optional<ProductType> productTypeOpt = productTypeRepository.findById(productType.getId());
        if (manufacturerOpt.isPresent() && productTypeOpt.isPresent()) {
            products.forEach(p -> prepareProduct(p, manufacturerOpt.get(), productTypeOpt.get()));
        }
        productRepository.saveAll(products);
        LOGGER.info("Products with type {} and manufacturer {} were saved to DB. List size {}",
                productType.getName(), manufacturer.getName(), products.size());
    }

    private void prepareProduct(Product product,
                                Manufacturer manufacturer,
                                ProductType productType) {
        product.setManufacturer(manufacturer);
        product.setProductType(productType);
    }
}
