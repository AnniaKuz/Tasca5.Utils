import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Print directory structure with all its files
 * indicating file type (directory > D, file > F) and last modification time
 * Save it in textFile .txt
 */

public class Main {
    static List<File> files = new ArrayList<>();


    public static void main(String[] args) {
        final String dirName = "DirectoryForJavaUtils";
        File file = new File(dirName);
        List<File> myfiles = doListing(file);
        try {
            PrintWriter writer = new PrintWriter("MyFiles.txt");
            for (File f : myfiles) {
                writer.print(f.getName() + " - " + f.lastModified() + " - ");
                if (f.isDirectory()) {
                    writer.print("D");
                } else if (f.isFile()) {
                    writer.print("F");
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
