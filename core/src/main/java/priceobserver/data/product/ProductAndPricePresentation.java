package priceobserver.data.product;

import priceobserver.data.productprice.ShortProductPriceProjection;
import priceobserver.dto.product.ProductDto;

public class ProductAndPricePresentation {
    private ProductDto product;
    private ShortProductPriceProjection price;

    public ProductAndPricePresentation(ProductDto product, ShortProductPriceProjection price) {
        this.product = product;
        this.price = price;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public ShortProductPriceProjection getPrice() {
        return price;
    }

    public void setPrice(ShortProductPriceProjection price) {
        this.price = price;
    }
}
