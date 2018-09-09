package com.easyweb.validator;

import java.io.IOException;
import java.net.URLClassLoader;
import java.util.stream.Collectors;
import javax.validation.Configuration;
import javax.validation.Validation;
import javax.validation.Validator;
import com.easyweb.lang.ClassUtils;

/**
 * 由于Validator是线程安全的，因此为了提高效率，使用单例validator
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public class SingletonValidator {
	private static SingletonValidator instance = null;
	Validator validator = null;

	private SingletonValidator() {
		Configuration<?> configuration = Validation.byDefaultProvider().configure();
		try {
			// 加载在classpath中找到的所有以 "-Validator.xml" 结尾的文件作为校验文件,包括jar中文件
			for (String str : ClassUtils.getAllFilesInClassPath((URLClassLoader) this.getClass().getClassLoader())
					.stream().filter(x -> x.endsWith("-Validator.xml")).collect(Collectors.toList())) {
				configuration.addMapping(this.getClass().getResourceAsStream(str));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (validator == null)
			validator = configuration.buildValidatorFactory().getValidator();
	}

	public static SingletonValidator getInstance() {
		if (instance == null)
			instance = new SingletonValidator();
		return instance;
	}

	public Validator getValidator() {
		return validator;
	}
}
