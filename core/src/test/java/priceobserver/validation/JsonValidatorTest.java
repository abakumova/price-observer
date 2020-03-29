package priceobserver.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class JsonValidatorTest {

    private static final String VALID_JSON = "{\"menu\": {\n" +
            "  \"id\": \"file\",\n" +
            "  \"value\": \"File\",\n" +
            "  \"popup\": {\n" +
            "    \"menuitem\": [\n" +
            "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\n" +
            "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\n" +
            "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\n" +
            "    ]\n" +
            "  }\n" +
            "}}";

    private static final String VALID_JSON_ARRAY = "[\"Sunday\", \"Monday\", \"Tuesday\", \"Wednesday\"," +
            " \"Thursday\", \"Friday\", \"Saturday\"]";

    private static final String INVALID_JSON = "[\n" +
            "    \"test\" : 123\n" +
            "]";

    private static final String INVALID_JSON_ARRAY = "[\"Sunday\" \"Monday\"]";

    private static final String JUST_A_TEXT = "I’m in love with a fairytale\n" +
            "Even though it hurts\n" +
            "’Cause I don’t care if I lose my mind\n" +
            "I’m already cursed";

    @Autowired
    private JsonValidator jsonValidator;

    @Test
    void shouldReturnTrueIfTheJsonIsValid() {
        assertTrue(jsonValidator.isValid(VALID_JSON, null));
        assertTrue(jsonValidator.isValid(VALID_JSON_ARRAY, null));
    }

    @Test
    void shouldReturnFalseIfTheJsonIsValid() {
        assertFalse(jsonValidator.isValid(JUST_A_TEXT, null));
        assertFalse(jsonValidator.isValid(INVALID_JSON, null));
        assertFalse(jsonValidator.isValid(INVALID_JSON_ARRAY, null));
    }
}