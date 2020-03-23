package priceobserver.data.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String encryptedPassword);
    Optional<User> findByEmail(String email);
}
