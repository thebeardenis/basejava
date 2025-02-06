package com.topjava.webapp;

import com.topjava.webapp.model.SectionType;

public class TestSingleton {
    private static TestSingleton instance;

    public static TestSingleton getInstance() {
        if (instance == null) {
            instance = new TestSingleton();
        }
        return instance;
    }
    private TestSingleton() {
    }

    public static void main(String[] args) {
        TestSingleton.getInstance().toString();
        Singleton insance = Singleton.valueOf("INSTANCE");
        System.out.println(insance);
        for (SectionType type : SectionType.values()) {
            System.out.println(type.getTitle());
        }
    }

    public enum Singleton{
        INSTANCE
    }
}
