package priceobserver.data.productproperties;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductPropertiesRepository extends CrudRepository<ProductProperties, Long> {

    Optional<ProductProperties> findFirstByProperties(String properties);
}
