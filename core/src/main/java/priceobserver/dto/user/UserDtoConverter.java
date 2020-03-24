package priceobserver.dto.user;

import org.springframework.stereotype.Component;
import priceobserver.data.user.User;
import priceobserver.dto.DtoConverter;

@Component
public interface UserDtoConverter extends DtoConverter<UserDto, User> {
}
