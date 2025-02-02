package com.topjava.webapp;

import com.topjava.webapp.model.Resume;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Resume r = new Resume("Pi");
        Class<? extends Resume> resumeClass = r.getClass();
        Field f = r.getClass().getDeclaredFields()[0];
        f.setAccessible(true);
        System.out.println(f.getName());
        System.out.println(f.get(r));
        f.set(r, "new uuid");

        Method m = resumeClass.getMethod("toString");
        Object result = m.invoke(r);
        System.out.println(result);
    }
}
