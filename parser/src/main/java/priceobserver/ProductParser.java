package priceobserver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import priceobserver.data.product.Product;

import java.io.IOException;
import java.util.List;

public interface ProductParser {

    /**
     * Get a list of Products by given URL to the products page.
     * For example, URL may look like "https://avic.ua/iphone.html".
     *
     * @param avicUrlWithProduct URL to products page
     * @return list of products
     */
    List<Product> parse(String avicUrlWithProduct);

    /**
     * Returns a document by given URL.
     *
     * @param url URL to document
     * @return document by given URL.
     */
    default Document getPageByUrl(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ProductParsingException(String.format("Error during getting document by provided url. Url: %s ", url), e);
        }
    }
}
