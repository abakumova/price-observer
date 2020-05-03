package priceobserver.avic;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import priceobserver.ImageSaverImpl;
import priceobserver.ProductParser;
import priceobserver.ProductParsingException;
import priceobserver.dto.product.ProductDto;
import priceobserver.dto.product.ProductDtoBuilder;
import priceobserver.dto.productproperties.ProductPropertiesDto;
import priceobserver.dto.productproperties.ProductPropertiesDtoBuilder;

import java.io.IOException;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class to parse products from Avic store site.
 */
@Component
public class AvicProductParser implements ProductParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvicProductParser.class);

    private static final String AVIC_DOMAIN = "https://avic.ua/";

    private static List<Document> productPages = new ArrayList<>();
    private static List<ProductDto> products = new ArrayList<>();

    public static void main(String[] args) {
        ProductParser parser = new AvicProductParser();
        //https://avic.ua/macbook.html https://avic.ua/iphone.html https://avic.ua/ipad.html
        List<ProductDto> productDtos = parser.parse("https://avic.ua/iphone.html");
        LOGGER.info("products list size {}", productDtos.size());
        productDtos.forEach(e -> LOGGER.info("\n{}\n", e));
    }

    @Override
    public List<ProductDto> parse(String avicUrlWithProduct) {
        LOGGER.info("Started parsing of the site {}", avicUrlWithProduct);
        fillProductPagesList(avicUrlWithProduct);
        productPages.forEach(this::extractProductAndAddToList);
        return products;
    }

    /**
     * Parse product page gallery to get product pages.
     */
    private void fillProductPagesList(String avicUrlWithProduct) {
        // Open the first part of page with products, get all product pages from it.
        Document startingPageWithProducts = getPageByUrl(avicUrlWithProduct);
        Elements linksToProductPages = startingPageWithProducts.select("div.images > a.img");
        linksToProductPages.forEach(a -> productPages.add(getPageByUrl(a.attr("href"))));

        //    The page with products is split into a few pages.
        //    Since we parsed the first part of it, we open others and getting product pages.
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
        String description = getDescription(el);
        //skip  products which marked as "Open box" or as "витринный вариант"
        if (fullProductName.toLowerCase().contains("open box")
                || description.toLowerCase().contains("витринный вариант")) {
            return;
        }

        //download product image by url, save to project folder and provide path to it
        Optional<String> pathToSavedImageOptional = saveImage(getImageUrl(el));
        String pathToImage = null;
        if (pathToSavedImageOptional.isPresent()) {
            pathToImage = pathToSavedImageOptional.get();
        }

        products.add(ProductDtoBuilder.aProductDto()
                .withName(getShortProductName(fullProductName))
                .withModel(getModelFromFullProductName(fullProductName))
                .withImage(pathToImage)
                .withDescription(description)
                .withYear(getYear(fullProductName))
                .withProductProperties(getProperties(el))
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
        Matcher matcher = Pattern.compile("\\(M.*\\)").matcher(fullProductName);
        String model = null;
        while (matcher.find()) {
            model = fullProductName.substring(matcher.start(), matcher.end()).replaceAll("[()]", "");
        }
        return model;
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
        String nameWithoutModel = nameWithoutCyrillic;
        int modelPositionIndex = nameWithoutCyrillic.lastIndexOf('(');
        if (modelPositionIndex != -1) {
            nameWithoutModel = nameWithoutCyrillic.substring(0, modelPositionIndex - 1);
        }

        return nameWithoutModel.replaceAll("[()]", "");
    }

    /**
     * Get src link of the image at the product page, and add Avic domain name to the get absolute path.
     *
     * @param el a product page
     * @return the absolute image url.
     */
    private String getImageUrl(Element el) {
        return AVIC_DOMAIN.concat(el.select("span > a > img.js_main-img").first().attr("src"));
    }

    private Optional<String> saveImage(String url) {
        return new ImageSaverImpl().saveImageByUrlToDefaultFolder(url);
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

        builder.delete(0, 1);
        return builder.toString();
    }

    /**
     * Gets a year of product made from full product name using regex.
     *
     * @param fullProductName a full product name
     * @return a year of product made or null if the year is absent in product name
     */
    private Year getYear(String fullProductName) {
        Matcher matcher = Pattern.compile("20\\d{2}").matcher(fullProductName);
        String year = null;
        while (matcher.find()) {
            year = fullProductName.substring(matcher.start(), matcher.end());
        }
        return year == null ? null : Year.of(Integer.parseInt(year.replace("[()]", "")));
    }

    private ProductPropertiesDto getProperties(Element el) {
        Elements rowsWithProperties = el.select("div.table-features > table > tbody > tr");
        return ProductPropertiesDtoBuilder.aProductPropertiesDto()
                .withProperties(getPropertiesAsJsonString(rowsWithProperties))
                .build();
    }

    private String getPropertiesAsJsonString(Elements elements) {
        StringBuilder builder = new StringBuilder("{");
        for (Element r : elements) {
            Elements keyAndValueEl = r.select("td");
            String key = keyAndValueEl.get(0).text();
            String value = keyAndValueEl.get(1).text();

            //skip rows with headers
            if (key.isEmpty() || value.isEmpty()) {
                continue;
            }

            builder.append("\"");
            builder.append(key.replace(":", ""));
            builder.append("\" : \"");
            builder.append(value.replace("\"", "'"));
            builder.append("\",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }
}
