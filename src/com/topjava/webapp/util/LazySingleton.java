package com.topjava.webapp.util;

public class LazySingleton {
    private static LazySingleton INSTANCE;

    double sin = Math.sin(4.2);

    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton INSTANCE = new LazySingleton();
    }


    public static LazySingleton getInstance() {

        return LazySingletonHolder.INSTANCE;

//        if (INSTANCE == null) {
//            synchronized (LazySingleton.class) {
//                if (INSTANCE == null) {
//                    INSTANCE = new LazySingleton();
//                }
//            }
//        }
//        return INSTANCE;
    }
}
