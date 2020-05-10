package priceobserver;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import priceobserver.data.product.Product;
import priceobserver.data.productproperties.ProductProperties;

import java.io.IOException;
import java.time.Year;
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

    /**
     * Get model from full product name.
     * For example, model from "Смартфон Apple iPhone 11 Pro 64GB Space Gray (MWC22)" will look like
     * "MWC22".
     *
     * @param fullProductName a full name of a product
     * @return string with model
     */
    String getModelFromFullProductName(String fullProductName);

    /**
     * Getting normal product name by cutting cyrillic prefix and model number.
     * For example, "Смартфон Apple iPhone 11 Pro 64GB Space Gray (MWC22)" will be cut to
     * "Apple iPhone 11 Pro 64GB Space Gray".
     *
     * @param fullProductName a full name of a product
     * @return short name of the product.
     */
    String getShortProductName(String fullProductName, String model);

    /**
     * Get src link of the image at the product page, and add Avic domain name to the get absolute path.
     *
     * @param el a product page
     * @return the absolute image url.
     */
    String getImageUrl(Element el);

    /**
     * On Avic site the description of a product is split into a few paragraphs.
     * We iterate through them and unit into the normal description.
     *
     * @param el a product page
     * @return description of the product
     */
    String getDescription(Element el);

    /**
     * Gets a year of product made from full product name using regex.
     *
     * @param fullProductName a full product name
     * @return a year of product made or null if the year is absent in product name
     */
    Year getYear(String fullProductName);

    /**
     * Returns a filled ProductProperties object.
     *
     * @param el page with a product
     * @return ProductProperties for current product
     */
    ProductProperties getProperties(Element el);
}
