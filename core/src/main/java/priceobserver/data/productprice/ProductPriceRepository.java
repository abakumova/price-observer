package priceobserver.data.productprice;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductPriceRepository extends CrudRepository<ProductPrice, Long> {

    @Query(value = "SELECT product_id AS productId, store_id as storeId, price, MAX(date) as date " +
            "FROM product_price " +
            "WHERE product_id IN (?1)GROUP BY productId",
            nativeQuery = true)
    List<ShortProductPriceProjection> findFreshPricesForProductsWithIds(List<Long> productsIds);
}
