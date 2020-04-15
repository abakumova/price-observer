package priceovserver.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import priceobserver.dto.product.ProductDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * It's just a first parser version. We need to get more experience in this deal before we start.
 */
public class Parser {

    private static final String AVIC_PAGE_WITH_IPHONES = "https://avic.ua/iphone.html";
    private static List<ProductDto> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect(AVIC_PAGE_WITH_IPHONES).get();

        Elements elements = document.getElementsByClass("box-product");

        elements.forEach(Parser::populateProductList);
    }

    private static void populateProductList(Element element) {
        System.out.println(element.select("span.name > a").first().text());
    }
}
