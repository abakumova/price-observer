package priceobserver.avic;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import priceobserver.ImageSaver;
import priceobserver.ProductParser;
import priceobserver.data.product.Product;
import priceobserver.data.product.ProductBuilder;
import priceobserver.data.productproperties.ProductProperties;
import priceobserver.data.productproperties.ProductPropertiesBuilder;

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

    private static final String ROUND_BRACES_REGEX = "[()]";

    private static final List<Document> productPages = new ArrayList<>();
    private static final List<Product> products = new ArrayList<>();

    private final ImageSaver imageSaver;

    @Autowired
    public AvicProductParser(ImageSaver imageSaver) {
        this.imageSaver = imageSaver;
    }

    @Override
    public List<Product> parse(String avicUrlWithProduct) {
        productPages.clear();
        products.clear();
        LOGGER.info("Started parsing of the site {}", avicUrlWithProduct);
        fillProductPagesList(avicUrlWithProduct);
        productPages.forEach(this::extractProductAndAddToList);
        return products;
    }

    @Override
    public String getModelFromFullProductName(String fullProductName) {
        String modelRegex = "\\([MZ].*\\)";
        Matcher matcher = Pattern.compile(modelRegex).matcher(fullProductName);
        String model = null;
        while (matcher.find()) {
            model = fullProductName.substring(matcher.start(), matcher.end())
                    .replaceAll(ROUND_BRACES_REGEX, "");
        }
        return model;
    }

    @Override
    public String getShortProductName(String fullProductName, String model) {
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

    @Override
    public String getImageUrl(Element el) {
        return el.select("div.swiper-slide > a.fancybox > img").first().attr("src");
    }

    private Optional<String> saveImage(String url) {
        return imageSaver.saveImageByUrlToDefaultFolder(url);
    }

    @Override
    public String getDescription(Element el) {
        Elements paragraphsWithDescription = el.select("div.col-lg-8.col-md-12 > div.content > p");
        StringBuilder builder = new StringBuilder();
        for (Element paragraph : paragraphsWithDescription) {
            builder.append(" ");
            builder.append(paragraph.text());
        }
        return builder.toString().trim().replaceAll(" +", " ");
    }

    @Override
    public Year getYear(String fullProductName) {
        String yearRegex = "(?:\\(|\\s)20\\d{2}";
        Matcher matcher = Pattern.compile(yearRegex).matcher(fullProductName);
        String year = null;
        while (matcher.find()) {
            year = fullProductName.substring(matcher.start(), matcher.end());
        }
        return year == null ? null : Year.of(Integer.parseInt(year.substring(1)));
    }

    @Override
    public ProductProperties getProperties(Element el) {
        Elements rowsWithProperties = el.select("div.characteristic-table > ul.characteristic-list > li");
        if (!rowsWithProperties.isEmpty()) {
            return ProductPropertiesBuilder.aProductProperties()
                                           .withProperties(getPropertiesAsJsonStringFromSpan(rowsWithProperties))
                                           .build();
        }
        rowsWithProperties = el.select("div.characteristic-table > table.pp-tab-characteristics-table > tbody > tr");
        return rowsWithProperties.isEmpty() ?
                null : ProductPropertiesBuilder.aProductProperties()
                                               .withProperties(getPropertiesAsJsonStringFromTable(rowsWithProperties))
                                               .build();
    }

    /**
     * Parse product page gallery to get product pages.
     */
    private void fillProductPagesList(String avicUrlWithProduct) {
        Document currentPage = getPageByUrl(avicUrlWithProduct);
        int countOfPages = Integer.parseInt(currentPage.select("ul.js_pagination > li.page-item").last().text());
        for (int i = 1; i <= countOfPages; i++) {
            currentPage = getPageByUrl(avicUrlWithProduct + "?page=" + i);
            Elements linksToProductPages = currentPage.select("div.prod-cart > div.prod-cart__top > a.js_go_product");
            linksToProductPages.forEach(a -> productPages.add(getPageByUrl(a.attr("href"))));
        }
    }

    /**
     * The method parse given product page, extract data from it into ProductDto object
     * and put the object into the List.
     *
     * @param el product page
     */
    private void extractProductAndAddToList(Document el) {
        String fullProductName = el.select("h1.page-title").first().text();
        String description = getDescription(el);
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
        String model = getModelFromFullProductName(fullProductName);
        products.add(ProductBuilder.aProduct()
                .withName(getShortProductName(fullProductName, model))
                .withModel(model)
                .withImage(pathToImage)
                .withDescription(description)
                .withYear(getYear(fullProductName))
                .withProductProperties(getProperties(el))
                .build());
    }

    private String getPropertiesAsJsonStringFromSpan(Elements elements) {
        StringBuilder builder = new StringBuilder("{");
        for (Element r : elements) {
            Elements keyAndValue = r.select("span");
            if (keyAndValue.isEmpty()) {
                continue;
            }
            String key = keyAndValue.first().text().replace(":", "");
            String value = keyAndValue.last().text();
            if (builder.toString().contains("\"" + key + "\" :")) {
                continue;
            }

            builder.append("\"");
            builder.append(key);
            builder.append("\" : \"");
            builder.append(value.replace("\"", "'"));
            builder.append("\",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }

    private String getPropertiesAsJsonStringFromTable(Elements elements) {
        StringBuilder builder = new StringBuilder("{");
        for (Element r : elements) {
            Elements keyAndValue = r.select("td");
            String key = keyAndValue.first().text();
            String value = keyAndValue.last().text();
            if (key.isBlank() || value.isBlank() || builder.toString().contains("\"" + key + "\" :")) {
                continue;
            }

            builder.append("\"");
            builder.append(key);
            builder.append("\" : \"");
            builder.append(value.replace("\"", "'"));
            builder.append("\",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("}");
        return builder.toString();
    }
}
