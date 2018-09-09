package com.easyweb.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test01 {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile(".*(.xls|.XLS)$");
        String fileName = "1111.XLSf";
        Matcher matcher = pattern.matcher(fileName);
        if(matcher.matches()){
            System.out.print("true");
        } else {
            System.out.print("false");
        }

	}

}
