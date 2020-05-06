package priceobserver.avic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priceobserver.ParsingManager;
import priceobserver.ProductParser;
import priceobserver.data.product.Product;
import priceobserver.data.product.ProductRepository;

import java.util.List;
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

    @Autowired
    public AvicParsingManager(ProductParser parser, ProductRepository productRepository) {
        this.parser = parser;
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> parsePages() {
        List<String> pages = List.of(PAGE_WITH_APPLE_WATCH,
                PAGE_WITH_IMACS,
                PAGE_WITH_IPADS,
                PAGE_WITH_IPHONES,
                PAGE_WITH_MACBOOKS);
        return pages.stream().flatMap(p -> parser.parse(p).stream()).collect(Collectors.toList());
    }

    @Override
    public void loadProducts(List<Product> products) {
        productRepository.saveAll(products);
    }
}
