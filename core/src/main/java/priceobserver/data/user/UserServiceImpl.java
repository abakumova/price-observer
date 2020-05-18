package priceobserver.data.user;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import priceobserver.dto.user.UserDto;
import priceobserver.dto.user.UserDtoConverter;

import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userConverter;

    public UserServiceImpl(UserRepository userRepository, UserDtoConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    @Override
    public Optional<UserDto> getByEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Null or blank email");
        }
        return userRepository.findByEmail(email).map(userConverter::convertToDto);
    }

    @Override
    @Transactional
    public void updateUser(UserDto user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        Optional<User> persistUserOpt = userRepository.findByEmail(user.getEmail());
        if (persistUserOpt.isPresent()) {
            User persistUser = persistUserOpt.get();
            persistUser.setFirstName(user.getFirstName());
            persistUser.setLastName(user.getLastName());
            persistUser.setBirth(user.getBirth());
            String password = user.getPassword();
            if (password != null && !password.isBlank()) {
                persistUser.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
            }
            userRepository.save(persistUser);
        }
    }
}
