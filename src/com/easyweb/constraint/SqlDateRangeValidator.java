package com.easyweb.constraint;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SqlDateRangeValidator 

implements ConstraintValidator<SqlDateRange,Date> {
	SqlDateRange constraintAnnotation;

	@Override
	public void initialize(SqlDateRange constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.constraintAnnotation = constraintAnnotation;
	}
	@Override
	public boolean isValid(Date date, ConstraintValidatorContext cvc) {
		Date start = null;
		Date end = null;
		SimpleDateFormat sdf = 
	    new SimpleDateFormat(constraintAnnotation.pattern());
		try {
			start = new Date(sdf.parse(constraintAnnotation.start()).getTime());
			end = new Date(sdf.parse(constraintAnnotation.end()).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if(date != null && start !=null && end !=null) {
			if(date.getTime()>=start.getTime() &&
					date.getTime()<=end.getTime())
				return true;
		}
		
		return false;
	}
	
}
