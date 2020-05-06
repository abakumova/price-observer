package priceobserver;

import priceobserver.data.product.Product;

import java.util.List;

public interface ParsingManager {

    List<Product> parsePages();

    void loadProducts(List<Product> products);
}
