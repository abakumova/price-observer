package priceobserver.dto.user;

import org.springframework.stereotype.Component;
import priceobserver.data.user.User;
import priceobserver.data.user.UserBuilder;
import priceobserver.data.userrole.UserRole;
import priceobserver.dto.userrole.UserRoleEnum;

import java.sql.Date;

@Component
public class UserDtoConverterImpl implements UserDtoConverter {

    @Override
    public UserDto convertToDto(User userEntity) {
        return UserDtoBuilder.anUserDto().withId(userEntity.getId())
                .withFirstName(userEntity.getFirstName())
                .withLastName(userEntity.getLastName())
                .withEmail(userEntity.getEmail())
                .withBirth(userEntity.getBirth().toLocalDate())
                .withUserRole(userEntity.getUserRole().getName())
                .withEncryptedPassword(userEntity.getEncryptedPassword())
                .withPassword(userEntity.getPassword())
                .withVersion(userEntity.getVersion())
                .withProfileImage(userEntity.getProfileImage())
                .build();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return UserBuilder.anUser().withId(userDto.getId())
                .withFirstName(userDto.getFirstName())
                .withLastName(userDto.getLastName())
                .withEmail(userDto.getEmail())
                .withBirth(Date.valueOf(userDto.getBirth()))
                .withUserRole(getUserRole(userDto.getUserRole()))
                .withEncryptedPassword(userDto.getEncryptedPassword())
                .withPassword(userDto.getPassword())
                .withVersion(userDto.getVersion())
                .withProfileImage(userDto.getProfileImage())
                .build();
    }

    private UserRole getUserRole(UserRoleEnum userRoleEnum) {
        UserRole userRole = new UserRole();
        userRole.setId(userRoleEnum.getId());
        userRole.setName(userRoleEnum.getRole());
        return userRole;
    }
}
