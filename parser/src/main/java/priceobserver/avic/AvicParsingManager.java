package priceobserver.avic;

import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AvicParsingManager implements ParsingManager {


    private static final String PAGE_WITH_MACBOOKS = "https://avic.ua/macbook.html";
    private static final String PAGE_WITH_IPHONES = "https://avic.ua/iphone.html";
    private static final String PAGE_WITH_IPADS = "https://avic.ua/ipad.html";
    private static final String PAGE_WITH_IMACS = "https://avic.ua/imac.html";
    private static final String PAGE_WITH_APPLE_WATCH = "https://avic.ua/apple-watch-umnie-chasi.html";

    private final ProductParser parser;
    private final ProductRepository productRepository;
    private final ManufacturerRepository manufacturerRepository;
    private final ProductTypeRepository productTypeRepository;

    @Autowired
    public AvicParsingManager(ProductParser parser, ProductRepository productRepository, ManufacturerRepository manufacturerRepository, ProductTypeRepository productTypeRepository) {
        this.parser = parser;
        this.productRepository = productRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.productTypeRepository = productTypeRepository;
    }

    @Override
    public List<Product> parsePages() {
        List<String> pages = List.of(PAGE_WITH_IPHONES);
        return pages.stream().flatMap(p -> parser.parse(p).stream()).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void loadProducts(List<Product> products) {
        Optional<Manufacturer> manufacturer = manufacturerRepository.findById(ManufacturerEnum.APPLE.getId());
        Optional<ProductType> productType = productTypeRepository.findById(ProductTypeEnum.SMARTPHONE.getId());
        if (manufacturer.isPresent() && productType.isPresent()) {
            products.forEach(p -> processProduct(p, manufacturer.get(), productType.get()));
        }
        productRepository.saveAll(products);
    }

    private void processProduct(Product product, Manufacturer manufacturer, ProductType productType) {
        product.setManufacturer(manufacturer);
        product.setProductType(productType);
    }
}
