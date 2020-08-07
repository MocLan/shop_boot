package com.moclan.shop.validation;


import com.moclan.shop.util.ErrorCode;
import com.moclan.shop.validation.impl.NotBlankImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(ElementType.FIELD)
@Constraint(validatedBy = NotBlankImpl.class)
@Retention(RUNTIME)
@Documented
public @interface NOTBLANK {
    String message() default ErrorCode.Code.NOT_BLANK;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
