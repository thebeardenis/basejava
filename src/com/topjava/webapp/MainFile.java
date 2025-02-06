package com.topjava.webapp;

import java.io.File;
import java.util.Arrays;
import java.util.List;

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
        if (directory.isFile()) {
            System.out.println("File: " + directory.getAbsolutePath());
        } else if (directory.isDirectory()) {
            System.out.println("Directory: " + directory.getAbsolutePath());
            File[] allFiles = directory.listFiles();
            List<File> lstFile = Arrays.asList(allFiles);
            for (File fileInDir : lstFile) {
                printFiles(fileInDir);
            }
        }
    }
}
