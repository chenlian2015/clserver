package com.cnd.greencube.server.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

//import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 * 
 * </p>
 * 
 * @author
 * @version 1.0
 */
public final class ReflectUtils {
	public static Object getInstance(Class clazz) {
		try {
			Object o = clazz.newInstance();
			return o;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param sClassName
	 * 
	 * @return Object
	 */
	static public Object newInstance(String sClassName) {
		try {
			Object objClass = Class.forName(sClassName).newInstance();

			return objClass;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * 
	 * @param sClassName
	 * 
	 * @return Object
	 */
	static public Object newInstance(Class clazz) {
		try {
			Object objClass = clazz.newInstance();

			return objClass;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param sClassName
	 * @param aclassParam
	 * @param aobjParam
	 * 
	 * @return Object
	 */
	static public Object newInstance(String sClassName, Class[] aclassParam, Object[] aobjParam) {
		try {
			Constructor objConstructor = Class.forName(sClassName).getConstructor(aclassParam);

			Object objClass = objConstructor.newInstance(aobjParam);

			return objClass;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param obj
	 *            Object
	 * @param sProperty
	 *            String
	 * 
	 * @return Object
	 */
	static public Object getProperty(Object obj, String sProperty) throws Exception {
		if (sProperty != null) {
			Method methodGetter = getPropertyGetterMethod(obj.getClass(), sProperty);
			return methodGetter.invoke(obj, null);
		} else {
			return null;
		}
	}


	/**
	 * 
	 * 
	 * @param obj
	 *            Object
	 * @param sProperty
	 *            String
	 * @param objValue
	 *            Object
	 * @throws Exception
	 */
	static public void setBeanProperty(Object obj, String sProperty, Object objValue) throws Exception {
		org.apache.commons.beanutils.PropertyUtils.setProperty(obj, sProperty, objValue);
	}

	/**
	 * 
	 * 
	 * @param obj
	 *            Object
	 * @param sProperty
	 *            String
	 * @param objValue
	 *            Object
	 * 
	 * @throws Exception
	 */
	static public void setProperty(Object obj, String sProperty, Object objValue) throws Exception {
		String sMethod = "set" + StringUtils.upperCaseTheFirstChar(sProperty);

		Class cls = obj.getClass();

		Field f = null;
		Class objClassType = null;
		try {
			f = objValue.getClass().getField("TYPE");
			objClassType = (Class) f.get(objValue);
			Method method = cls.getMethod(sMethod, new Class[] { objClassType });
			method.invoke(obj, new Object[] { objValue });
			return;
		} catch (Exception e) {
		}

		try {
			try {
				Method method = cls.getMethod(sMethod, new Class[] { objValue.getClass() });
				method.invoke(obj, new Object[] { objValue });
				return;
			} catch (Exception nsme) {
			}

			Method[] methods = cls.getMethods();
			int count = 0;
			for (int i = 0; i < methods.length; i++) {
				if (methods[i].getName().equalsIgnoreCase(sMethod)) {
					count++;
				}
			}

			if ((f != null && objClassType != null) || objValue.getClass() == java.lang.String.class || objValue.getClass() == java.math.BigDecimal.class
					|| objValue.getClass() == java.math.BigInteger.class) {
				if (count == 1) {
					for (int i = 0; i < methods.length; i++) {
						if (methods[i].getName().equalsIgnoreCase(sMethod)) {
							Class[] cs = methods[i].getParameterTypes();
							if (cs[0] == java.lang.String.class) {
								methods[i].invoke(obj, new Object[] { objValue.toString() });
							} else if (cs[0].isPrimitive()) {
								if (cs[0] == Integer.TYPE)
									methods[i].invoke(obj, new Object[] { Integer.valueOf(objValue.toString()) });
								else if (cs[0] == Long.TYPE)
									methods[i].invoke(obj, new Object[] { Long.valueOf(objValue.toString()) });
								else if (cs[0] == Short.TYPE)
									methods[i].invoke(obj, new Object[] { Short.valueOf(objValue.toString()) });
								else if (cs[0] == Boolean.TYPE) {
									try {
										int trueOrFalse = Integer.parseInt(objValue.toString());
										methods[i].invoke(obj, new Object[] { Boolean.valueOf(trueOrFalse == 1) });
									} catch (Exception e) {
										methods[i].invoke(obj, new Object[] { Boolean.valueOf(objValue.toString()) });
									}
								} else if (cs[0] == Character.TYPE)
									methods[i].invoke(obj, new Object[] { new Character(objValue.toString().charAt(0)) });
								else if (cs[0] == Byte.TYPE)
									methods[i].invoke(obj, new Object[] { Byte.valueOf(objValue.toString()) });
								else if (cs[0] == Float.TYPE)
									methods[i].invoke(obj, new Object[] { Float.valueOf(objValue.toString()) });
								else if (cs[0] == Double.TYPE)
									methods[i].invoke(obj, new Object[] { Double.valueOf(objValue.toString()) });
							}
							return;
						}
					}
				}
			} else
				throw new Exception("�޷��ҵ�����:" + obj.getClass() + "��" + sMethod + "(" + objValue.getClass() + ") ������");
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 
	 * @param obj
	 *            Object
	 * @param objValue
	 *            Object
	 * 
	 * @throws Exception
	 */
	static public void remove(Object obj, Object objValue) throws Exception {
		try {
			String sMethod = "remove";

			Class[] aobjClass = { Object.class };
			Object[] aobjArg = { objValue };
			obj.getClass().getMethod(sMethod, aobjClass).invoke(obj, aobjArg);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 
	 * 
	 * @param obj
	 *            Object
	 * @param sProperty
	 *            String
	 * 
	 * @return Class
	 */
	static public Class getPropertyType(Object obj, String sProperty) {
		try {
			if (sProperty != null) {
				String sMethod = "get" + StringUtils.upperCaseTheFirstChar(sProperty);

				Method methodGet = obj.getClass().getMethod(sMethod, null);
				return methodGet.getReturnType();
			}

			return null;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param sMethodName
	 *            String
	 * 
	 * @return String
	 */
	static public String getPropertyNameFromMethodName(String sMethodName) {
		String sPropertyName = null;

		if (sMethodName.indexOf("get") == 0 || sMethodName.indexOf("set") == 0) {
			sPropertyName = sMethodName.substring(3);
		} else if (sMethodName.indexOf("is") == 0) {
			sPropertyName = sMethodName.substring(2);
		}

		return sPropertyName;
	}

	/**
	 * 
	 * 
	 * @param objMain
	 *            Object
	 * @param sChildName
	 *            String
	 * @param objChild
	 *            Object
	 * 
	 * @throws Exception
	 */
	static public void addChildMethod(Object objMain, String sChildName, Object objChild) throws Exception {
		String sMethod = "add" + StringUtils.upperCaseTheFirstChar(sChildName);
		Class[] aclassArg = { objChild.getClass() };
		Method method = objMain.getClass().getMethod(sMethod, aclassArg);
		Object[] aobjArg = { objChild };
		method.invoke(objMain, aobjArg);
	}


	/**
	 * 
	 * 
	 * @param classObject
	 *            Class
	 * @param classProp
	 *            Class
	 * 
	 * @return List
	 */
	static public List getPropertyGetterMethods(Class classObject, Class classProp) {
		List listProp = new ArrayList();

		try {
			Method[] aobjMethod = classObject.getMethods();

			if (aobjMethod != null) {
				Method objMethod;
				for (int i = 0; i < aobjMethod.length; i++) {
					objMethod = aobjMethod[i];
					if (classProp == null || classProp == objMethod.getReturnType()) {
						if (isGetterMethod(classObject, objMethod.getName())) {
							listProp.add(objMethod);
						}
					}
				}
			}
		} catch (Exception e) {
			return null;
		}

		if (listProp.size() > 0) {
			return listProp;
		} else {
			return null;
		}
	}

	/**
	 * 
	 * 
	 * @param classObject
	 *            Class
	 * @param sPropName
	 *            String
	 * 
	 * @throws Exception
	 * 
	 * @return Method
	 */
	static public Method getPropertyGetterMethod(Class classObject, String sPropName) throws Exception {
		sPropName = StringUtils.upperCaseTheFirstChar(sPropName);

		Method methodGetter;

		try {
			methodGetter = classObject.getMethod("get" + sPropName, null);
		} catch (NoSuchMethodException e) {
			methodGetter = classObject.getMethod("is" + sPropName, null);
		}

		return methodGetter;
	}

	/**
	 * 
	 * 
	 * @param classObject
	 *            Class
	 * @param sMethodName
	 *            String
	 * 
	 * @return boolean
	 */
	static public boolean isGetterMethod(Class classObject, String sMethodName) {
		String sPropertyName = null;

		if (sMethodName.indexOf("get") == 0) {
			sPropertyName = sMethodName.substring(3);
		} else if (sMethodName.indexOf("is") == 0) {
			sPropertyName = sMethodName.substring(2);
		}

		if (sPropertyName != null) {
			return isProperty(classObject, sPropertyName);
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * @param classObject
	 *            Class
	 * @param sMethodName
	 *            String
	 * 
	 * @return boolean
	 */
	static public boolean isSetterMethod(Class classObject, String sMethodName) {
		String sPropertyName = null;

		if (sMethodName.indexOf("set") == 0) {
			sPropertyName = sMethodName.substring(3);
		}

		if (sPropertyName != null) {
			return isProperty(classObject, sPropertyName);
		} else {
			return false;
		}
	}

	/**
	 * 
	 * 
	 * @param classObject
	 *            Class
	 * @param sPropName
	 *            String
	 * 
	 * @return boolean
	 */
	static public boolean isProperty(Class classObject, String sPropName) {
		sPropName = StringUtils.upperCaseTheFirstChar(sPropName);

		Method methodGetter = null;

		try {
			methodGetter = classObject.getMethod("get" + sPropName, null);
		} catch (NoSuchMethodException e) {
			try {
				methodGetter = classObject.getMethod("is" + sPropName, null);
			} catch (NoSuchMethodException e1) {
				return false;
			}
		}

		if (methodGetter.getParameterTypes().length != 0) {
			return false;
		}

		try {
			Class[] aclassParam = { methodGetter.getReturnType() };
			classObject.getMethod("set" + sPropName, aclassParam);

			return true;
		} catch (NoSuchMethodException e2) {
			return false;
		}
	}

	public static Class loadClass(String className) throws ClassNotFoundException {
		Class cls = null;
		try {
			cls = Thread.currentThread().getContextClassLoader().loadClass(className.trim());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (cls == null) {
			cls = Class.forName(className.trim());
		}

		return cls;
	}
}
