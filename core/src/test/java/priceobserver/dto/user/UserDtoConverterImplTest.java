package priceobserver.dto.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import priceobserver.data.user.User;
import priceobserver.data.user.UserBuilder;
import priceobserver.data.userrole.UserRole;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserDtoConverterImplTest {

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "First name";
    private static final String LAST_NAME = "Last name";
    private static final String EMAIL = "email@email.email";
    private static final String ENCRYPTED_PASSWORD = "Encrypted password";
    private static final String PASSWORD = "Password";
    private static final String USER_ROLE_AS_STRING = "user";
    private static final LocalDate BIRTH = LocalDate.now();
    private static final Integer VERSION = 1;
    private static final UserRole USER_ROLE = new UserRole();
    private static final Byte ROLE_ID = (byte) 2;


    private static User userEntity;
    private static UserDto userDto;

    @Autowired
    private UserDtoConverter userDtoConverter;

    @BeforeAll
    static void setUp() {
        USER_ROLE.setName(USER_ROLE_AS_STRING);
        USER_ROLE.setId(ROLE_ID);

        userEntity = UserBuilder.anUser()
                .withId(USER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withEncryptedPassword(ENCRYPTED_PASSWORD)
                .withPassword(PASSWORD)
                .withBirth(Date.valueOf(BIRTH))
                .withVersion(VERSION)
                .withUserRole(USER_ROLE)
                .build();

        userDto = UserDtoBuilder.anUserDto()
                .withId(USER_ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .withEmail(EMAIL)
                .withEncryptedPassword(ENCRYPTED_PASSWORD)
                .withPassword(PASSWORD)
                .withBirth(BIRTH)
                .withVersion(VERSION)
                .withUserRole(USER_ROLE_AS_STRING)
                .build();
    }

    @Test
    void shouldConvertToDto() {
        UserDto userConvertedToDto = userDtoConverter.convertToDto(userEntity);

        assertEquals(USER_ID, userConvertedToDto.getId());
        assertEquals(FIRST_NAME, userConvertedToDto.getFirstName());
        assertEquals(LAST_NAME, userConvertedToDto.getLastName());
        assertEquals(ENCRYPTED_PASSWORD, userConvertedToDto.getEncryptedPassword());
        assertEquals(PASSWORD, userConvertedToDto.getPassword());
        assertEquals(EMAIL, userConvertedToDto.getEmail());
        assertEquals(BIRTH, userConvertedToDto.getBirth());
        assertEquals(VERSION, userConvertedToDto.getVersion());
        assertEquals(USER_ROLE.getName(), userConvertedToDto.getUserRole().getRole());
        assertEquals(USER_ROLE.getId(), userConvertedToDto.getUserRole().getId());
    }

    @Test
    void shouldConvertToEntity() {
        User userDtoConvertedToEntity = userDtoConverter.convertToEntity(userDto);

        assertEquals(USER_ID, userDtoConvertedToEntity.getId());
        assertEquals(FIRST_NAME, userDtoConvertedToEntity.getFirstName());
        assertEquals(LAST_NAME, userDtoConvertedToEntity.getLastName());
        assertEquals(ENCRYPTED_PASSWORD, userDtoConvertedToEntity.getEncryptedPassword());
        assertEquals(PASSWORD, userDtoConvertedToEntity.getPassword());
        assertEquals(EMAIL, userDtoConvertedToEntity.getEmail());
        assertEquals(BIRTH, userDtoConvertedToEntity.getBirth().toLocalDate());
        assertEquals(VERSION, userDtoConvertedToEntity.getVersion());
        assertEquals(USER_ROLE.getName(), userDtoConvertedToEntity.getUserRole().getName());
        assertEquals(USER_ROLE.getId(), userDtoConvertedToEntity.getUserRole().getId());
    }
}