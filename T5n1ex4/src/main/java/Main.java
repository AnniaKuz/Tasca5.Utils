import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    static List<File> files = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        final String dirName = "DirectoryForJavaUtils";
        File file = new File(dirName);
        List<File> myfiles = doList(file);
        try {
            PrintWriter writer = new PrintWriter("MyFiles.txt");
            for (File f : myfiles) {
                writer.print(f.getName() + " - " + f.lastModified() + " - ");
                if (f.isDirectory()) {
                    writer.print("D");
                } else if (f.isFile()) {
                    writer.print("F");
                    System.out.println(f.getName());
                    String readText = "";
                    while((!readText.equalsIgnoreCase("yes")) ||
                            (!readText.equalsIgnoreCase("no"))){
                    System.out.println("Do you want to read this file. Yes/No");
                    readText = scan.next();
                    if (readText.equalsIgnoreCase("yes")) {
                        String currentLine = "";
                        BufferedReader obj = new BufferedReader(new FileReader(f));
                        while ((currentLine = obj.readLine()) != null) {
                            System.out.println(currentLine);
                            scan = new Scanner(System.in);
                        }
                    } else if (readText.equalsIgnoreCase("no")) {
                    }
                    break;
                }
                } else {
                    writer.print("???");
                }
                writer.println();
            }
            writer.close();
            System.out.println("Check your files in MyFiles.txt ");
        }catch(IOException e){
            System.out.println("Something went wrong");
        }
    }

    public static List<File> doList(File dirName) {
        File[] fileList = dirName.listFiles();
        for (File file : fileList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                files.add(file);
                doList(file);
            }
        }
        return files;
    }

}

