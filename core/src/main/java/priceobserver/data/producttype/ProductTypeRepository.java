package priceobserver.data.producttype;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductTypeRepository extends CrudRepository<ProductType, Long> {

    Optional<ProductType> findFirstByName(String name);
}
