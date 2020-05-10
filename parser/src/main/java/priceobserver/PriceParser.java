package priceobserver;

import priceobserver.data.productprice.ProductPrice;

import java.util.List;

public interface PriceParser {

    List<ProductPrice> parse(String url);
}
