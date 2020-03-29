package priceobserver.validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * A custom realisation of ConstraintValidator to validate a JSON
 * or a JSON array.
 */
@Component
public class JsonValidator implements ConstraintValidator<ValidJson, String> {
    @Override
    public void initialize(ValidJson constraintAnnotation) {
        //Auto generated method stub
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            new JSONObject(value);
        } catch (JSONException ex) {

            try {
                new JSONArray(value);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}
