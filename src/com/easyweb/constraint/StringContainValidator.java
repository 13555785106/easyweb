package com.easyweb.constraint;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StringContainValidator

		implements ConstraintValidator<StringContain, String> {
	StringContain constraintAnnotation;

	@Override
	public void initialize(StringContain constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
		this.constraintAnnotation = constraintAnnotation;
	}

	@Override
	public boolean isValid(String strs, ConstraintValidatorContext cvc) {
		if (strs == null)
			strs = "";
		Set<String> itemsSet = new HashSet<String>();
		for (String str : Arrays.asList(constraintAnnotation.items().split(constraintAnnotation.separator()))) {
			str = str.trim();
			if (!str.isEmpty())
				itemsSet.add(str);
		}
		if (itemsSet.size() == 0)
			throw new RuntimeException("可选项不能为空");

		Set<String> selectedSet = new HashSet<String>();
		for (String str : Arrays.asList(strs.split(constraintAnnotation.separator()))) {
			str = str.trim();
			if (!str.isEmpty())
				selectedSet.add(str);
		}
		int num = 0;

		for (String str : selectedSet) {
			if (itemsSet.contains(str)) {
				num++;
			} else if (!str.isEmpty())
				return false;
		}
		if (num >= constraintAnnotation.num())
			return true;

		return false;
	}

}
