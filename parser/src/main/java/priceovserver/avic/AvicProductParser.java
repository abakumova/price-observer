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
import priceobserver.dto.productproperties.ProductPropertiesDto;
import priceovserver.ProductParser;
import priceovserver.ProductParsingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class to parse products from Avic store site.
 */
@Component
public class AvicProductParser implements ProductParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(AvicProductParser.class);

    private static final String AVIC_PAGE_WITH_IPHONES = "https://avic.ua/iphone.html";
    private static final String PRODUCT_ELEMENT_CSS_CLASS_NAME = "box-product";

    private static List<ProductDto> products = new ArrayList<>();

    @Override
    public List<ProductDto> parse() throws ProductParsingException {
        try {
            LOGGER.info("Started parsing of the site {}", AVIC_PAGE_WITH_IPHONES);
            getElementsWithProducts().forEach(this::extractProductAndAddToList);
        } catch (IOException e) {
            LOGGER.error("Error in product parsing process. Site: {} ", AVIC_PAGE_WITH_IPHONES, e);
            throw new ProductParsingException(e);
        }

        return products;
    }

    private Elements getElementsWithProducts() throws IOException {
        Document document = Jsoup.connect(AVIC_PAGE_WITH_IPHONES).get();
        return document.getElementsByClass(PRODUCT_ELEMENT_CSS_CLASS_NAME);
    }

    private void extractProductAndAddToList(Element el) {
        String fullProductName = el.select("span.name > a").first().text();

        //Using regex expression to get product model from full product name
        Pattern pattern = Pattern.compile("(M.+)");
        Matcher matcher = pattern.matcher(fullProductName);
        String model = null;

        while (matcher.find()) {
            model = fullProductName.substring(matcher.start(), matcher.end() - 1);
        }
        //Getting normal product name by cutting cyrillic prefix
        String name = fullProductName.substring(fullProductName.indexOf(' ') + 1);
        String photo = el.select("a.img > img").first().attr("src");
        String properties = getProductProperties(el.select("div.description > p").first().text());

        products.add(ProductDtoBuilder.aProductDto()
                .withName(name)
                .withModel(model)
                .withImage(photo)
                .withProductProperties(new ProductPropertiesDto(properties))
                .build());
    }

    private String getProductProperties(String rawProperties) {
        return rawProperties.replace("/", ",");
    }


    public static void main(String[] args) throws ProductParsingException {
        ProductParser parser = new AvicProductParser();
        parser.parse().forEach(e -> LOGGER.info("{}\n", e));
    }
}
