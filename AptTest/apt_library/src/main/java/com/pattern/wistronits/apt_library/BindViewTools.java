package com.pattern.wistronits.apt_library;

import android.app.Activity;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindViewTools {
    public static void bind(Activity activity) {
        Class clazz = activity.getClass();
        try {
            Class bindViewClass = Class.forName(clazz + "_ViewBinding");
            Method method = bindViewClass.getMethod("bind", bindViewClass);
            try {
                method.invoke(bindViewClass.newInstance(), activity);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
