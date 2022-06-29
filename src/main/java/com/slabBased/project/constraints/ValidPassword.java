package com.slabBased.project.constraints;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)

public @interface ValidPassword {
    String message() default "Invalid Password!! password must contain atleast 1 uppercase, 1 lowercase, 1 special character and 1 digit";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
