package priceobserver.dto.user;

import priceobserver.data.user.User;
import priceobserver.dto.DtoConverter;

public class UserDtoConverterImpl implements DtoConverter<UserDto, User> {

    @Override
    public UserDto convertToDto(User userEntity) {
        return UserDtoBuilder.anUserDto().withId(userEntity.getId())
                                         .withFirstName(userEntity.getFirstName())
                                         .withLastName(userEntity.getLastName())
                                         .withEmail(userEntity.getEmail())
                                         .withBirth(userEntity.getBirth().toLocalDate())
                                         .withUserRole(userEntity.getUserRole().getName())
                                         .build();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return null;
    }
}
