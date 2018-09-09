package com.easyweb.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = { SqlDateRangeValidator.class })
@Target({ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SqlDateRange {
	String message() default "";
	String start() default "";
	String end() default "";
	String pattern() default "yyyy-MM-dd";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
