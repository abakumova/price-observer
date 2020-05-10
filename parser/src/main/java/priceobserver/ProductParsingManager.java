package priceobserver;

import priceobserver.data.manufacturer.ManufacturerEnum;
import priceobserver.data.product.Product;
import priceobserver.dto.producttype.ProductTypeEnum;

import java.util.List;

public interface ProductParsingManager {

    void loadProducts(List<Product> products,
                      ManufacturerEnum manufacturer,
                      ProductTypeEnum productType);

    void run();
}
