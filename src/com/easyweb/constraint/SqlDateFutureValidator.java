package com.easyweb.constraint;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * java.sql.Date类型将来时校验器。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SqlDateFutureValidator implements ConstraintValidator<SqlDatePast, Date> {
	SqlDatePast constraintAnnotation;

	@Override
	public void initialize(SqlDatePast constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(Date date, ConstraintValidatorContext arg1) {
		java.util.Date now = new java.util.Date();
		String str = constraintAnnotation.value().trim();
		if (!str.isEmpty()) {
			String pattern = constraintAnnotation.pattern().trim();
			if (pattern.isEmpty())
				pattern = "yyyy-MM-dd";
			try {
				now = new SimpleDateFormat(pattern).parse(str);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (date != null && date.getTime() < now.getTime()) {
			return false;
		}
		return true;
	}
}
