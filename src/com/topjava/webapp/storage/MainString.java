package com.topjava.webapp.storage;

public class MainString {
    public static void main(String[] args) {
        String[] strArr = new String[] {"1", "2", "3", "4"};
        StringBuilder sb = new StringBuilder();
        for (String stor: strArr) {
            sb.append(stor).append(", ");
        }
        System.out.println(sb.toString());

        String str1 = "abc";
        String str3 = "c";
        String str2 = "ab"+str3;
        System.out.println(str1 == str2);
    }
}
