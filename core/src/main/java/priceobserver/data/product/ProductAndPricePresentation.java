package priceobserver.data.product;

import priceobserver.data.productprice.ShortProductPriceProjection;
import priceobserver.dto.product.ProductDto;

import java.util.Objects;

public class ProductAndPricePresentation {
    private ProductDto product;
    private boolean hasProductInWishList = false;
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

    public boolean isHasProductInWishList() {
        return hasProductInWishList;
    }

    public void setHasProductInWishList(boolean hasProductInWishList) {
        this.hasProductInWishList = hasProductInWishList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductAndPricePresentation that = (ProductAndPricePresentation) o;
        return hasProductInWishList == that.hasProductInWishList &&
                Objects.equals(product, that.product) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, hasProductInWishList, price);
    }

    @Override
    public String toString() {
        return "ProductAndPricePresentation{" +
                "product=" + product +
                ", hasProductInWishList=" + hasProductInWishList +
                ", price=" + price +
                '}';
    }
}
