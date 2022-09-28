package main.java;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, FileNotFoundException {



            String[] myList1 = new File("DirectoryForJavaUtils").list();
            Arrays.sort(myList1);
            for (String o : myList1) {
                System.out.println(o);
            }
    }
}
