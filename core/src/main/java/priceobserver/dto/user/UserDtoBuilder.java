package priceobserver.dto.user;

import java.time.LocalDate;

public final class UserDtoBuilder {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birth;
    private String encryptedPassword;
    private String password;
    private Integer version;
    private String profileImage;
    private UserRoleEnum userRole;

    private UserDtoBuilder() {
    }

    public static UserDtoBuilder anUserDto() {
        return new UserDtoBuilder();
    }

    public UserDtoBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserDtoBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserDtoBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserDtoBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDtoBuilder withBirth(LocalDate birth) {
        this.birth = birth;
        return this;
    }

    public UserDtoBuilder withEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
        return this;
    }

    public UserDtoBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDtoBuilder withVersion(Integer version) {
        this.version = version;
        return this;
    }

    public UserDtoBuilder withProfileImage(String profileImage) {
        this.profileImage = profileImage;
        return this;
    }

    public UserDtoBuilder withUserRole(UserRoleEnum userRole) {
        this.userRole = userRole;
        return this;
    }

    public UserDto build() {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setEmail(email);
        userDto.setBirth(birth);
        userDto.setEncryptedPassword(encryptedPassword);
        userDto.setPassword(password);
        userDto.setVersion(version);
        userDto.setProfileImage(profileImage);
        userDto.setUserRole(userRole);
        return userDto;
    }
}
