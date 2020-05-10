package priceobserver;

import org.jsoup.nodes.Document;

public interface ParsingUtils {

    /**
     * Returns a document by given URL.
     *
     * @param url URL to document
     * @return document by given URL.
     */
    Document getPageByUrl(String url);
}
