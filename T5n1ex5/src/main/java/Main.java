import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

    public static final String FILE_NAME = "test.ser";

    public static void main(String[] args) {
        Object object1 = new Object("Samsung", 1000, "Smartphone");

        serializeInFile(object1);

        System.out.println(deserializeFromFile());
    }

        public static void serializeInFile(Object object1){
            try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME)))
            {
                oos.writeObject(object1);
                oos.close();
                System.out.println("File has been written");
            }
            catch(Exception ex){

                System.out.println(ex.getMessage());
            }
        }
        public static Object deserializeFromFile(){
            Object object2 = null;
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME)))
            {

                object2=(Object)ois.readObject();
            }
            catch(Exception ex){

                System.out.println(ex.getMessage());
            }

            return object2;

        }
    }

