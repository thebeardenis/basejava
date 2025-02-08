package com.topjava.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        String filepath = "../basejava";
        File directory = new File("../basejava/src");
        /*
        File file = new File(filepath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error",e);
        }
        File dir = new File("/home/thebear/mygit/basejava/src/com/topjava/webapp");
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {
                System.out.println(name);
            }
        }
         try (FileInputStream fis = new FileInputStream(filepath)) {
             System.out.println(fis.read());
        } catch (IOException e) {
             throw new RuntimeException(e);
         } */

        printFiles(directory);
    }

    public static void printFiles(File directory) {
        File[] allFiles = directory.listFiles();
        System.out.println("\n Directory: "+directory.getAbsolutePath()+"\n In directory:");
        if (allFiles != null) {
            for (File fileInDir : allFiles) {
                if (fileInDir.isDirectory()) {
                    System.out.println("D: "+fileInDir.getAbsolutePath());
                    printFiles(fileInDir);
                } else if (fileInDir.isFile()) {
                    System.out.println("F: " + directory.getAbsolutePath());
                }
            }
        }
    }
}
