package priceovserver.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Parser {

    private static final String AVIC_PAGE_WITH_IPHONES = "https://avic.ua/iphone.html";

    public static void main(String[] args) throws IOException {

        Document document = Jsoup.connect(AVIC_PAGE_WITH_IPHONES).get();

        Elements elements = document.getElementsByClass("box-product");

        elements.forEach((System.out::println));
    }
}
