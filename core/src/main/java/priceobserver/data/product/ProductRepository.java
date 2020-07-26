package priceobserver.data.product;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Long> {

    Optional<Product> findFirstByName(String name);

    List<Product> findAllByNameContainingOrModelContaining(String nameContains,
                                                           String modelContains,
                                                           Pageable pageable);

    long countAllByNameContainingOrModelContaining(String nameContains, String modelContains);

    @Query("SELECT p FROM Product p WHERE p.name = ?1 AND p.model = ?2")
    List<Product> findFirstByNameAndModel(String name, String model);

    List<Product> findAllByProductTypeId(Long id);

    List<Product> findAllByProductTypeId(Long id, Pageable pageable);

    long countAllByProductTypeId(Long id);
}
