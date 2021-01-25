package priceobserver.data.productprice;

import java.time.LocalDate;

public interface ShortProductPriceProjection {

    Long getStoreId();
    Long getProductId();
    LocalDate getDate();
    Float getPrice();
}
