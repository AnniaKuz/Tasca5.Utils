import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class Main {
    static List<File> files = new ArrayList<>();

    public static void main(String[] args) {
        final String dirName = "DirectoryForJavaUtils";
        File file = new File(dirName);
        List<File> myfiles = doListing(file);
            for (File f : myfiles) {
                System.out.print(f.getName() + " - " + f.lastModified() + " - ");
                if (f.isDirectory()) {
                    System.out.print("D");
                } else if (f.isFile()) {
                    System.out.print("F");
                }
                System.out.println();
            }
    }

    public static List<File> doListing(File dirName) {
        File[] fileList = dirName.listFiles();
        for (File file : fileList) {
            if (file.isFile()) {
                files.add(file);
            } else if (file.isDirectory()) {
                files.add(file);
                doListing(file);
            }
        }
        return files;
    }

}

