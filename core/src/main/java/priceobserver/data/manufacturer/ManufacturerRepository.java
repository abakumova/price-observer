package priceobserver.data.manufacturer;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ManufacturerRepository extends CrudRepository<Manufacturer, Long> {

    Optional<Manufacturer> findFirstByName(String name);
}
