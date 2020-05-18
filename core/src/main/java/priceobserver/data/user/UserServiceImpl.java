package priceobserver.data.user;

import org.springframework.stereotype.Component;
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
}
