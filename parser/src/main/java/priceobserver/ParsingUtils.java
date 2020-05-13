package priceobserver;

import org.jsoup.nodes.Document;

public interface ParsingUtils {

    String ROUND_BRACES_REGEX = "[()]";
    float PRICE_ABSENT = -1F;

    /**
     * Returns a document by given URL.
     *
     * @param url URL to document
     * @return document by given URL.
     */
    Document getPageByUrl(String url);

    String getModel(String fullProductName);
}
