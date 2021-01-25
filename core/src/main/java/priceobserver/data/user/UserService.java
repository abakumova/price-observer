package priceobserver.data.user;

import priceobserver.dto.user.UserDto;

import java.security.Principal;
import java.util.Optional;

public interface UserService {

    UserDto getUser(Principal principal);

    Optional<UserDto> getUser(String email);

    void updateUser(UserDto user);

    void saveNewUser(UserDto user);

    boolean isEmailUsed(String email);
}
