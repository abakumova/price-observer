package priceobserver;

import java.util.List;
import priceobserver.data.manufacturer.ManufacturerEnum;
import priceobserver.data.product.Product;
import priceobserver.dto.producttype.ProductTypeEnum;

public interface ParsingManager {

    void loadProducts(List<Product> products, ManufacturerEnum manufacturer,
        ProductTypeEnum productType);

    void run();
}
