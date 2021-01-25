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

    @Query(value = "SELECT * FROM product_price WHERE product_id = ?1 GROUP BY product_id, date ORDER BY date DESC",
    nativeQuery = true)
    List<ProductPrice> getPriceForProductWithId(Long id);
}
