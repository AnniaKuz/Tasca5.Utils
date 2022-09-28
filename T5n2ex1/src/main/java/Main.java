import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * From exercises T5n1ex3
 * Parameterize all the methods in configuration file.
 *
 *
 * 1)to parameterize:
 * -Directory
 * -Name and directory of TXT file
 *
 *
 */


        public class Main {
            static List<File> files = new ArrayList<>();


            public static <FileOutStream> void main(String[] args) throws IOException {

                Properties properties = new Properties();

                final String dirName = "DirectoryForJavaUtils";
                File file = new File(dirName);
                doListing(file, properties);
                System.out.println("Done2");

                for(int i = 0; i < properties.size(); i++){
                    System.out.println(properties.getProperty("Directory"));

                }

            }

    public static Properties doListing(File dirName, Properties properties ) throws IOException {
        File[] fileList = dirName.listFiles();
        int length = fileList.length;
        int i = 0;
            for (File file : fileList) {
                if (file.isDirectory()) {
                    properties.setProperty("Directory", file.getName());
                    doListing(file, properties);
                    String nameDir = file.getName();
                } else if (file.isFile()) {
                    properties.setProperty("Directory name", file.getName());
                }
            }

        final String PATH = "src\\application.properties";

        FileOutputStream outputStrem = new FileOutputStream(PATH);
        properties.store(outputStrem, "Your properties file");
        System.out.println("Done1");

        return properties;
    }





}
