package priceobserver.data.store;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StoreRepository extends CrudRepository<Store, Long> {

    Optional<Store> findFirstByName(String name);
}
