package com.easyweb.utils;

import java.io.UnsupportedEncodingException;

/**
 * 编码转换工具 
 */
public final class CodecUtils {

	private CodecUtils() {
	}

	/**
	 * 将value字符串按指定的编码encoding转换成字节数组，
	 * 再将字节数组转换成八进制形式表达的字符串。
	 * 
	 * @param value 字符串（如：'中国'）。
	 * @param encoding 字符串（如：'UTF-8'）。
	 * @return 返回八进制 编码格式的字符串（如：'\344\270\255\345\233\275'）。
	 */
	public static String encodeOct(String value, String encoding) throws UnsupportedEncodingException {
		byte[] bytes = value.getBytes(encoding);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			String octStr = Integer.toOctalString(0xff & b);
			sb.append("\\");
			sb.append(octStr);
		}
		return sb.toString();
	}

	/**
	 * 将八进制编码格式字符串转换成 encoding 格式的字符串（包含中英文）。
	 * 
	 * @param value 八进制的字节字符串（如：'\344\270\255\345\233\275'）。
	 * @param encoding 编码（如：'UTF-8'）。
	 * @return 返回字符串（如：'中国'）。
	 */
	public static String decodeOct(String value, String encoding) throws UnsupportedEncodingException {
		String[] strs = value.substring(1).split("\\\\");
		byte[] bytes = new byte[strs.length];
		for (int i = 0; i < strs.length; i++) {
			bytes[i] = (byte) Integer.parseInt(strs[i], 8);
		}
		return new String(bytes, "UTF-8");
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "中国";
		System.out.println(str);
		str = encodeOct(str,"UTF-8");
		System.out.println(str);
		String str2 = decodeOct(str,"UTF-8");
		System.out.println(str2);
	}

}
