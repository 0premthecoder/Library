import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class Library{
    ArrayList<String> books = new ArrayList<>();
    public void writeTxt(){
        try {
            FileWriter fileWriter = new FileWriter("Book.txt");
            for (String bookname: books) {
                fileWriter.write(bookname + "\n");
            }
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void addBookwithTxt(String line){
        books.add(line);
    }
    public  Library(String name, int id, String owner){
        System.out.println("*********************" + name + "*********************");
        System.out.println("ID : LIB" + id);
        System.out.println("Owner is " +owner);
        File myFile = new File("Book.txt");
        try {
            Scanner sc = new Scanner(myFile);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                addBookwithTxt(line);
            }
            sc.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void addBook(String name, String author){
        books.add(name + " - " +author);
        System.out.println("Book Was Successfully Added");

    }
    public void showAvailableBook(){
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) +". "+ books.get(i));
        }
        if (books.isEmpty()){
            System.out.println("Books are not available");
        }

    }
    public  void returnBook(String bookname, String author){
        if (books.isEmpty()){
            System.out.println("Books are not available");
        }
        else {
            addBook(bookname, author);
            System.out.println("Returned successfully");
        }
    }
    public void issueBook(String bookname, String author){
        if (books.isEmpty()){
            System.out.println("Books are not available");
        }
        else {
            books.remove(bookname + " - "+author);
            System.out.println("issued successfully");
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Library library = new Library("Book Prasad", 89058, "Mr Jitendra Kumar");
        Scanner s = new Scanner(System.in);
        System.out.println("Use S for show available");
        System.out.println("Use A for Add Book");
        System.out.println("Use I Issue Book");
        System.out.println("Use R for Return Book");
        while (true){
            String input = s.nextLine().toLowerCase(Locale.ROOT);
            if (input.equals("s") ){
                library.showAvailableBook();
            }
            if (input.equals("a")){
                System.out.println("Write book name");
                String book = s.nextLine();
                System.out.println("Write author name");
                String author = s.nextLine();
                library.addBook(book, author);
            }
            if (input.equals("i")){
                System.out.println("Write book name");
                String book = s.nextLine();
                System.out.println("Write author name");
                String author = s.nextLine();
                library.issueBook(book, author);
            }
            if (input.equals("r")){
                System.out.println("Write Book Name");
                String book = s.nextLine();
                System.out.println("Write author name");
                String author = s.nextLine();
                library.returnBook(book, author);
            }
            if (input.equals("e")){
                System.out.println("Exiting....");
                library.writeTxt();
                return;
            }
        }

    }
}


//        File myFile = new File("Book.txt");
//        try {
//            myFile.createNewFile();
//        } catch (IOException e) {
//            System.out.println("Unable to create this file");
//            e.printStackTrace();
//        }