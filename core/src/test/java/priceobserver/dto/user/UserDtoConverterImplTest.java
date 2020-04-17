package priceobserver.dto.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import priceobserver.data.user.User;
import priceobserver.dto.DtoTestConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedUserDto;
import static priceobserver.dto.testutils.DtoTestPreparationHelper.getPreparedUserEntity;

@SpringBootTest
@ContextConfiguration(classes = DtoTestConfiguration.class)
class UserDtoConverterImplTest {

    private static User userEntity;
    private static UserDto userDto;

    @Autowired
    private UserDtoConverter userDtoConverter;

    @BeforeAll
    static void setUp() {
        userEntity = getPreparedUserEntity();
        userDto = getPreparedUserDto();
    }

    @Test
    void shouldConvertToDto() {
        assertEquals(userDto, userDtoConverter.convertToDto(userEntity));
    }

    @Test
    void shouldConvertToEntity() {
        assertEquals(userEntity, userDtoConverter.convertToEntity(userDto));
    }
}
