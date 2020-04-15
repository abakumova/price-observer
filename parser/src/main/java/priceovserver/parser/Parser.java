package priceovserver.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import priceobserver.dto.product.ProductDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * It's just a first parser version. We need to get more experience in this deal before we start.
 */
public class Parser {

    private static final String AVIC_PAGE_WITH_IPHONES = "https://avic.ua/iphone.html";
    private static List<ProductDto> products = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.out.println("Started parsing of " + AVIC_PAGE_WITH_IPHONES);
        Document document = Jsoup.connect(AVIC_PAGE_WITH_IPHONES).get();

        Elements elements = document.getElementsByClass("box-product");

        elements.forEach(Parser::populateProductList);
    }

    private static void populateProductList(Element el) {
        String name = el.select("span.name > a").first().text();

        Pattern pattern = Pattern.compile("(M.+)");
        Matcher matcher = pattern.matcher(name);
        while (matcher.find()) {
            System.out.println("Model: " + name.substring(matcher.start(), matcher.end() - 1));
        }

        System.out.println("Name: " + name.substring(name.indexOf(' ') + 1));
        //Photo, but it has relative path.
        System.out.println("Photo: " + el.select("a.img > img").first().attr("src"));
        //Price (M.+)
        System.out.println("Price: " + el.select("span.price").first().text().replaceFirst("грн", ""));
        //Description
        System.out.println("Description: " + el.select("div.description > p").first().text());

        System.out.println("\n");
    }
}
