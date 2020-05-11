package priceobserver.avic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import priceobserver.ParsingUtils;
import priceobserver.PriceParser;
import priceobserver.data.product.Product;
import priceobserver.data.product.ProductRepository;
import priceobserver.data.productprice.ProductPrice;
import priceobserver.data.productprice.ProductPriceBuilder;
import priceobserver.data.productprice.ProductPriceRepository;
import priceobserver.data.store.Store;
import priceobserver.data.store.StoreRepository;
import priceobserver.dto.store.StoreEnum;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static priceobserver.ParsingUtils.PRICE_ABSENT;
import static priceobserver.ParsingUtils.ROUND_BRACES_REGEX;

@Component
public class AvicPriceParser implements PriceParser {


    private final ParsingUtils parsingUtils;
    private final ProductRepository productRepository;
    private final ProductPriceRepository priceRepository;
    private final StoreRepository storeRepository;

    public AvicPriceParser(ParsingUtils parsingUtils, ProductRepository productRepository, ProductPriceRepository priceRepository, StoreRepository storeRepository) {
        this.parsingUtils = parsingUtils;
        this.productRepository = productRepository;
        this.priceRepository = priceRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    @Transactional
    public void parse(String url) {
        Document currentPage = parsingUtils.getPageByUrl(url);
        int countOfPages = Integer.parseInt(currentPage.select("ul.js_pagination > li.page-item").last().text());
        for (int i = 1; i <= countOfPages; i++) {
            processPage(parsingUtils.getPageByUrl(url + "?page=" + i));
        }
    }

    @Override
    public void savePrice(String name, String model, float price) {
        List<Product> products = productRepository.findFirstByNameAndModel(name, model);
        Optional<Store> store = storeRepository.findById(StoreEnum.AVIC.getId());
        if (products != null && store.isPresent()) {
            ProductPrice pPrice = ProductPriceBuilder.aProductPrice()
                                                     .withPrice(price)
                                                     .withDate(LocalDate.now())
                                                     .withStore(store.get())
                                                     .build();
            for (Product p : products) {
                pPrice.setProduct(p);
                priceRepository.save(pPrice);
            }
        }
    }

    @Override
    public void processPage(Document page) {
        Elements elements = page.select("div.item-prod.col-lg-3");
        for (Element el : elements) {
            String fullProductName = el.select("a.js_go_product > div.prod-cart__descr").text();
            if (fullProductName.toLowerCase().contains("open box")) {
                continue;
            }
            String model = parsingUtils.getModel(fullProductName);
            String name = getProductName(fullProductName, model);
            float price = getPrice(el);
            savePrice(name, model, price);
        }
    }

    private String getProductName(String fullProductName, String model) {
        String nameWithoutCyrillic = fullProductName.substring(fullProductName.indexOf(' ') + 1);
        String nameWithoutModel = nameWithoutCyrillic;
        if (model != null) {
            nameWithoutModel = nameWithoutCyrillic.replace(model, "");
        }

        return nameWithoutModel
                .replaceAll(ROUND_BRACES_REGEX, "")
                .trim()
                .replaceAll(" +", " ");
    }

    private float getPrice(Element el) {
        String price = el.select("div.prod-cart__prise > div.prod-cart__prise-new")
                .text()
                .replace("грн", "");
        return price.isBlank() ? PRICE_ABSENT : NumberUtils.parseNumber(price, Long.class);
    }
}
