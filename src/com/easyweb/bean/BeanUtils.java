package com.easyweb.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.easyweb.bean.editor.DateEditor;
import com.easyweb.bean.editor.SqlDateEditor;
import com.easyweb.bean.editor.SqlTimeEditor;
import com.easyweb.bean.editor.SqlTimestampEditor;
import com.sample.db.model.User;


/**
 * 一个工具类。实现POJO与MAP之间的互转。
 * 
 * @author 肖俊峰
 * @since 1.0
 * @version 1.0
 */
public final class BeanUtils {
	static {
		// 除了基本数据类型，其它类型必须有对应的PropertyEditor。
		// 并在转换之前，进行相应的注册。
		PropertyEditorManager.registerEditor(java.util.Date.class, DateEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Date.class, SqlDateEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Time.class, SqlTimeEditor.class);
		PropertyEditorManager.registerEditor(java.sql.Timestamp.class, SqlTimestampEditor.class);
	}

	private BeanUtils() {
	}

	public static Map<String, String> bean2MapStr(Object bean) {
		return bean2MapStr(bean, "");
	}

	public static Map<String, Object> bean2Map(Object bean) {
		return bean2Map(bean, "");
	}

	public static Map<String, String> bean2MapStr(Object bean, String namePrefix) {
		return bean2MapStr(bean, namePrefix, ",");
	}

	public static Map<String, String> bean2MapStr(Object bean, String namePrefix, String arraySeparator) {
		Map<String, Object> mapObj = bean2Map(bean);
		Map<String, String> mapStr = new HashMap<String, String>();
		for (Map.Entry<String, Object> entry : mapObj.entrySet()) {
			String key = namePrefix + entry.getKey();
			String val = "";
			PropertyEditor pe = null;
			Object propVal = entry.getValue();
			if (propVal != null) {
				if (propVal.getClass().isArray()) {
					pe = PropertyEditorManager.findEditor(propVal.getClass().getComponentType());
					if (pe != null) {
						int arrayLength = Array.getLength(propVal);
						for (int i = 0; i < arrayLength; i++) {
							pe.setValue(Array.get(propVal, i));
							val += pe.getAsText();
							if (i < arrayLength - 1)
								val += arraySeparator;
						}
					}
				} else {
					pe = PropertyEditorManager.findEditor(propVal.getClass());
					if (pe != null) {
						pe.setValue(propVal);
						val = pe.getAsText();
					}
				}
			}
			mapStr.put(key, val);
		}
		return mapStr;
	}

	public static Map<String, Object> bean2Map(Object bean, String namePrefix) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				String name = propertyDescriptor.getName();
				if (!name.equals("class")) {
					try {
						map.put(namePrefix + propertyDescriptor.getName(),
								propertyDescriptor.getReadMethod().invoke(bean));
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return map;
	}

	public static <T> T mapStr2Bean(Map<String, String> map, Class<T> clazz) {
		return mapStr2Bean(map, clazz, "");
	}

	public static <T> T mapStr2Bean(Map<String, String> map, Class<T> clazz, String namePrefix) {
		return mapStr2Bean(map, clazz, namePrefix, ",");
	}

	public static <T> T mapStr2Bean(Map<String, String> map, Class<T> clazz, String namePrefix, String arraySeparator) {
		T t = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			t = clazz.newInstance();
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				try {
					String propName = pd.getName();
					if (!propName.equals("class")) {
						String value = map.get(namePrefix + propName);
						if (pd.getPropertyType() == String.class) {
							pd.getWriteMethod().invoke(t, value);
						} else if (pd.getPropertyType().isArray()) {
							Class<?> componentType = pd.getPropertyType().getComponentType();
							String[] strs = value.split(arraySeparator);
							PropertyEditor pe = PropertyEditorManager.findEditor(componentType);
							if (strs != null) {
								Object array = Array.newInstance(componentType, strs.length);
								for (int i = 0; i < strs.length; i++) {
									pe.setAsText(strs[i]);
									Array.set(array, i, pe.getValue());
								}
								pd.getWriteMethod().invoke(t, array);
							}
						} else {
							PropertyEditor pe = PropertyEditorManager.findEditor(pd.getPropertyType());
							if (pe != null) {
								pe.setAsText(value);
								// System.out.println(pe.getValue().getClass());
								pd.getWriteMethod().invoke(t, pe.getValue());
							}
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}

		return t;
	}

	public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz) {
		return map2Bean(map, clazz, "");
	}

	// 判断是否是可转换的数值类型
	public static boolean numCanConvert(Class<?> srcClass, Class<?> dstClass) {
		if ((srcClass == java.math.BigInteger.class || srcClass == java.math.BigDecimal.class
				|| srcClass == Double.class || srcClass == double.class || srcClass == Float.class
				|| srcClass == float.class || srcClass == Long.class || srcClass == long.class
				|| srcClass == Integer.class || srcClass == int.class || srcClass == Short.class
				|| srcClass == short.class) &&

				(dstClass == Double.class || dstClass == double.class || dstClass == Float.class
						|| dstClass == float.class || dstClass == Long.class || dstClass == long.class
						|| dstClass == Integer.class || dstClass == int.class || dstClass == Short.class
						|| dstClass == short.class)

		)
			return true;
		return false;
	}

	// 如果数值类型不匹配，使用double类型作为中间类型进行转换，可能丢失精度。
	public static Object numConvert(Object srcValue, Class<?> dstClass) {
		double ret = 0;
		if (srcValue.getClass() == java.math.BigInteger.class)
			ret = ((java.math.BigInteger) srcValue).longValue();
		else if (srcValue.getClass() == java.math.BigDecimal.class)
			ret = ((java.math.BigDecimal) srcValue).doubleValue();
		else
			ret = (double) srcValue;

		if (dstClass == Double.class || dstClass == double.class) {
			return ret;
		} else if (dstClass == Float.class || dstClass == float.class) {
			return (float) ret;
		} else if (dstClass == Long.class || dstClass == long.class) {
			return (long) ret;
		} else if (dstClass == Integer.class || dstClass == int.class) {
			return (int) ret;
		} else if (dstClass == Short.class || dstClass == short.class) {
			return (short) ret;
		}
		return null;
	}

	// 如果日期类型不匹配，使用java.util.Date作为中间类型进行转换，可能丢失精度。

	public static Object dateConvert(Object srcValue, Class<?> dstClass) {
		try {
			Class<?> srcClass = srcValue.getClass();
			Method getTime = srcClass.getMethod("getTime");
			long val = (long) getTime.invoke(srcValue);
			@SuppressWarnings("rawtypes")
			Constructor ctr = dstClass.getConstructor(long.class);
			Object date = ctr.newInstance(val);
			return date;
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T map2Bean(Map<String, Object> map, Class<T> clazz, String namePrefix) {
		T t = null;
		BeanInfo beanInfo = null;
		try {
			beanInfo = Introspector.getBeanInfo(clazz);
			t = clazz.newInstance();
			for (PropertyDescriptor propertyDescriptor : beanInfo.getPropertyDescriptors()) {
				String propName = propertyDescriptor.getName();
				if (!propName.equals("class")) {
					Object srcValue = map.get(namePrefix + propName);
					Class<?> dstClass = propertyDescriptor.getPropertyType();
					Method writeMethod = propertyDescriptor.getWriteMethod();
					if (srcValue != null) {
						Class<?> srcClass = srcValue.getClass();
						try {
							if (dstClass == srcClass) {
								writeMethod.invoke(t, srcValue);
							} else {
								if (numCanConvert(srcClass, dstClass)) {
									writeMethod.invoke(t, numConvert(srcValue, dstClass));
								} else if (srcValue instanceof java.util.Date) {
									writeMethod.invoke(t, dateConvert(srcValue, dstClass));
								} else
									System.out.println(
											propName + " 无法将 " + srcClass.getName() + " 转换为 " + dstClass.getName());
							}
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}
				}

			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		return t;
	}

	public static void main(String[] args) {
		System.out.println(new java.util.Date().getTime());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("account", "GGGG");
		map.put("salary", new BigDecimal(9));
		map.put("birthday", new java.sql.Timestamp(1536154428162L));
		User user = map2Bean(map, User.class);
		System.out.println(user);
		System.out.println(Long.MAX_VALUE);
		double d = Long.MAX_VALUE;
		System.out.println((long) d);

	}
}
