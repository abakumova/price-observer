package priceovserver.avic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.product.ProductDtoBuilder;
import priceovserver.ProductParser;
import priceovserver.ProductParsingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class to parse products from Avic store site.
 */
@Component
public class AvicProductParser implements ProductParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvicProductParser.class);

    private static final String AVIC_DOMAIN = "https://avic.ua/";
    private static final String AVIC_PAGE_WITH_IPHONES = "https://avic.ua/iphone.html";

    private static List<Document> productPages = new ArrayList<>();
    private static List<ProductDto> products = new ArrayList<>();

    @Override
    public List<ProductDto> parse(String avicUrlWithProduct) {
        LOGGER.info("Started parsing of the site {}", avicUrlWithProduct);
        fillProductPagesList();
        productPages.forEach(this::extractProductAndAddToList);
        return products;
    }

    private void fillProductPagesList() {
        // Open the first part of page with products, get all product pages from it.
        Document startingPageWithProducts = getPageByUrl(AVIC_PAGE_WITH_IPHONES);
        Elements linksToProductPages = startingPageWithProducts.select("div.images > a.img");
        linksToProductPages.forEach(a -> productPages.add(getPageByUrl(a.attr("href"))));

        /*
            The page with products is split into a few pages.
            Since we parsed the first part of it, we open others and getting product pages.
        */
        Elements linksToNextPages = startingPageWithProducts.select("div.paging > a.ditto_page");
        List<Document> otherPagesWithProducts = new ArrayList<>();
        linksToNextPages.forEach(a -> otherPagesWithProducts.add(getPageByUrl(AVIC_DOMAIN.concat(a.attr("href")))));

        for (Document doc : otherPagesWithProducts) {
            linksToProductPages = doc.select("div.images > a.img");
            linksToProductPages.forEach(a -> productPages.add(getPageByUrl(a.attr("href"))));
        }
    }

    private Document getPageByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error("Error during getting document by provided url. Url: {} ", url, e);
            throw new ProductParsingException(e);
        }
    }

    private void extractProductAndAddToList(Document el) {
        String fullProductName = el.select("div.left > h1 > span").first().text();
        //skip  product which marked as "Open box"
        if (fullProductName.contains("(Open Box)")) {
            return;
        }

        String model = getModelFromFullProductName(fullProductName);

        //Getting normal product name by cutting cyrillic prefix
        String name = fullProductName.substring(fullProductName.indexOf(' ') + 1);
        String photo = AVIC_DOMAIN.concat(el.select("span > a > img.js_main-img").first().attr("src"));
        String description = parseDescription(el);

        products.add(ProductDtoBuilder.aProductDto()
                .withName(name)
                .withModel(model)
                .withImage(photo)
                .withDescription(description)
                .build());
    }

    private String getModelFromFullProductName(String fullProductName) {
        return fullProductName.substring(fullProductName.lastIndexOf('(') + 1, fullProductName.length() - 1);
    }


    private String parseDescription(Element el) {
        Elements paragraphsWithDescription = el.select("div.about-product > p");
        StringBuilder builder = new StringBuilder();
        for (Element paragraph : paragraphsWithDescription) {
            builder.append(" ");
            builder.append(paragraph.text());
        }
        return builder.toString();
    }


    public static void main(String[] args) {
        ProductParser parser = new AvicProductParser();
        parser.parse(AVIC_PAGE_WITH_IPHONES).forEach(e -> LOGGER.info("\n{}\n", e));
    }
}
