import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


public class Main {
    static List<File> files = new ArrayList<>();
    static final String dirName = "DirectoryForJavaUtils";

    static List<String> encryptedFiles = new ArrayList<>();

    public static void main(String[] args) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {

        File file = new File(dirName);
        List<File> myfiles = doListing(file);

        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator key = KeyGenerator.getInstance("AES");
        key.init(128,secureRandom);
        SecretKey myKey = key.generateKey();
        byte [] bs = new byte[16];
        secureRandom.nextBytes(bs);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bs);

            printEncrypted(myfiles, myKey,ivParameterSpec);
        System.out.println("----------------------------------------");
            printDecrypted(encryptedFiles, myKey,ivParameterSpec);

    }
    public static void printEncrypted(List<File> myfiles,SecretKey myKey ,IvParameterSpec iv) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
        for (File f : myfiles) {
            String nameFile = f.getName();
            byte[] textEncrypted = encrypt(myKey, nameFile,iv);
            encryptedFiles.add(Base64.getEncoder().encodeToString(textEncrypted));
            System.out.println(Base64.getEncoder().encodeToString(textEncrypted));
        }
    }

    public static void printDecrypted(List<String> encryptedFiles, SecretKey myKey,IvParameterSpec iv) throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        for(String textEncrypted:encryptedFiles){
            byte[] text = Base64.getDecoder()
                    .decode(textEncrypted);
            String textDecrypted = decrypt(text, myKey,iv);
            System.out.println(textDecrypted);
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

    public static byte[] encrypt(SecretKey myKey, String nameFile,IvParameterSpec iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE,myKey,iv);

        byte[] encrypted = cipher.doFinal(nameFile.getBytes());
        return encrypted;
    }

    public static String decrypt(byte[] text, SecretKey myKey,IvParameterSpec iv) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, myKey, iv);
        byte[] textdecypted = cipher.doFinal(text);
         return(new String(textdecypted));

    }

}

