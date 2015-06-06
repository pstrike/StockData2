package utility.json;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import utility.log.LoggerManager;

public class JsonConverter {
	public static Map toMap(Object javaBean) {
	
	    Map result = new HashMap();
	    Method[] methods = javaBean.getClass().getDeclaredMethods();
	
	    for (Method method : methods) {
	
	        try {
	
	            if (method.getName().startsWith("get")) {
	
	                String field = method.getName();
	                field = field.substring(field.indexOf("get") + 3);
	                field = field.toLowerCase().charAt(0) + field.substring(1);
	
	                Object value = method.invoke(javaBean, (Object[]) null);
	                result.put(field, null == value ? "" : value.toString());
	
	            }
	
	        } catch (Exception e) {
	        	LoggerManager.getInstance().getLogger().error("Json Convert Util Err");
	        	LoggerManager.getInstance().getLogger().error(e);
	            e.printStackTrace();
	        }
	
	    }
	
	    return result;
	
	}
	
	public static JSONObject toJSON(Object bean) {
	
	    return new JSONObject(toMap(bean));
	
	}
}