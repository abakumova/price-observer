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

    /**
     * Parse product page gallery to get product pages.
     */
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

    /**
     * Returns a document by given URL.
     *
     * @param url URL to document
     * @return document by given URL.
     */
    private Document getPageByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            LOGGER.error("Error during getting document by provided url. Url: {} ", url, e);
            throw new ProductParsingException(e);
        }
    }

    /**
     * The method parse given product page, extract data from it into ProductDto object
     * and put the object into the List.
     *
     * @param el product page
     */
    private void extractProductAndAddToList(Document el) {
        String fullProductName = el.select("div.left > h1 > span").first().text();
        //skip  products which marked as "Open box"
        if (fullProductName.contains("(Open Box)")) {
            return;
        }

        products.add(ProductDtoBuilder.aProductDto()
                .withName(getShortProductName(fullProductName))
                .withModel(getModelFromFullProductName(fullProductName))
                .withImage(getPhotoUrl(el))
                .withDescription(getDescription(el))
                .build());
    }

    /**
     * Get model from full product name.
     * For example, model from "Смартфон Apple iPhone 11 Pro 64GB Space Gray (MWC22)" will look like
     * "MWC22".
     *
     * @param fullProductName a full name of a product
     * @return string with model
     */
    private String getModelFromFullProductName(String fullProductName) {
        return fullProductName.substring(fullProductName.lastIndexOf('(') + 1, fullProductName.length() - 1);
    }

    /**
     * Getting normal product name by cutting cyrillic prefix and model number.
     * For example, "Смартфон Apple iPhone 11 Pro 64GB Space Gray (MWC22)" will be cut to
     * "Apple iPhone 11 Pro 64GB Space Gray".
     *
     * @param fullProductName a full name of a product
     * @return short name of the product.
     */
    private String getShortProductName(String fullProductName) {
        String nameWithoutCyrillic = fullProductName.substring(fullProductName.indexOf(' ') + 1);
        return nameWithoutCyrillic.substring(0, nameWithoutCyrillic.indexOf('(') - 1);
    }

    /**
     * Get src link of the image at the product page, and add Avic domain name to the get absolute path.
     *
     * @param el a product page
     * @return the absolute image url.
     */
    private String getPhotoUrl(Element el) {
        return AVIC_DOMAIN.concat(el.select("span > a > img.js_main-img").first().attr("src"));
    }

    /**
     * On Avic site the description of a product is split into a few paragraphs.
     * We iterate through them and unit into the normal description.
     *
     * @param el a product page
     * @return description of the product
     */
    private String getDescription(Element el) {
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
