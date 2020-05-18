package priceobserver.data.user;

import priceobserver.dto.user.UserDto;

import java.util.Optional;

public interface UserService {

    Optional<UserDto> getByEmail(String email);

    void updateUser(UserDto user);
}
