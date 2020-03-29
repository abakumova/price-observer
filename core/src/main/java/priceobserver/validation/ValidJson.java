package priceobserver.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * A custom realisation of validation annotation to validate JSON or JSON object.
 * Accepts String.
 */
@Constraint(validatedBy = {JsonValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface ValidJson {
    String message() default "invalid JSON or JSON array";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
