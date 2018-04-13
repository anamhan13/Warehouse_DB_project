package main;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionClass<T> {
	
	public ReflectionClass() {	
	}

	@SuppressWarnings("unused")
	public List<Object> retrieveProperties(T obj) {
		List<Object> list = new ArrayList<Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true); 
			Object value = new Object();
			try {
				PropertyDescriptor pd = new PropertyDescriptor(obj.getClass().getDeclaredFields()[0].getName(), obj.getClass());
				Method method = pd.getReadMethod();
				value = method.invoke(obj);
				list.add(field.get(obj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
