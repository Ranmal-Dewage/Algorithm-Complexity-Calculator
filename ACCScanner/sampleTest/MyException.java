import java.io.*;

public class MyException {
    static void accessFiles() throws FileNotFoundException {
        try {
            FileReader f = new FileReader("D:\\Exceptions.doc");
            System.out.println("File found");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage(asa));
            throw e; // Rethrows the exception
        }
    }

    public static void main(String[] args) {
        try {
            accessFiles();
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found!");
        }
    }
}