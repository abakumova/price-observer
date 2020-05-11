package priceobserver;

import org.jsoup.nodes.Document;
import org.springframework.transaction.annotation.Transactional;

public interface PriceParser {

    void parse(String url);

    @Transactional
    void savePrice(String name, String model, float price);

    void processPage(Document page);
}
