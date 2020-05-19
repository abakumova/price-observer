package priceobserver.data.wishproduct;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WishProductRepository extends CrudRepository<WishProduct, Long> {
    List<WishProduct> findAllByUserIdAndProductIdIn(Long userId, List<Long> productIds);

    List<WishProduct> findAllByUserId(Long userId);

    List<WishProduct> deleteFirstByUserIdAndProductId(Long userId, Long productId);
}