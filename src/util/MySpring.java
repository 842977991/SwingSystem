package util;

import java.util.HashMap;

public class MySpring {
    //IOC 控制对象反转
    //对象的控制权交给当前类

    //为了存储对象
    private static HashMap<String,Object> beanBox = new HashMap<>();

    public static <T>T getBean(String className){
        T object = null;
        try {
            object = (T)beanBox.get(className);
            if(object == null){
                Class clazz = Class.forName(className);
                object = (T)clazz.newInstance();
                beanBox.put(className,object);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }
}
