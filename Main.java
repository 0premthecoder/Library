
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class Library{
    ArrayList<String> books = new ArrayList<>();
    ArrayList<String> returnBook = new ArrayList<>();
    private void writeBookList(){
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
    private void  writingIssuedList(){
        try {
            FileWriter fileWriter = new FileWriter("Issued.txt");
            for (String issue: returnBook) {
                fileWriter.write(issue + "\n");
            }
            fileWriter.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
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
                books.add(line);
            }
            sc.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
        File issueFile = new File("issued.txt");
        try {
            Scanner sc = new Scanner(issueFile);
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                returnBook.add(line);
            }
            sc.close();
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void addBook(String name, String author){
        books.add(name + " - " +author);
        System.out.println("Book Was Successfully Added");
        writeBookList();
    }
    public void showAvailableBook(){
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) +". "+ books.get(i));
        }
        if (books.isEmpty()){
            System.out.println("Books are not available");
        }

    }
    public  void returnBook(String bookname, String author, String user){
        if (books.isEmpty()){
            System.out.println("Books are not available");
        }
        else if(books.contains(bookname + " - "+author)){
            System.out.println("Book was already in Library");
        }
        else if (returnBook.contains(bookname + " - "+author + " Given to " + user)){
            returnBook.remove(bookname + " - "+author + " Given to " + user);
            addBook(bookname, author);
            System.out.println("Returned successfully");
        }
        else {
            System.out.println("You have Entered Something Wrong");
        }
        writingIssuedList();
        writeBookList();
    }
    public void issueBook(String bookname, String author, String user){
        if (books.isEmpty()){
            System.out.println("Books are not available");
        }
        else if (books.contains(bookname + " - "+author)) {
            books.remove(bookname + " - "+author);
            returnBook.add(bookname + " - "+author + " Given to " + user);
            System.out.println("issued successfully");
        }

        else {
            System.out.println("This Book is Not Available Here");
        }
        writeBookList();
        writingIssuedList();
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
        System.out.println("Press E for Exit");

        while (true){
            String input = s.nextLine().toLowerCase(Locale.ROOT);
            if (input.equals("s") ){
                library.showAvailableBook();
            }
            if (input.equals("a")){
                System.out.println("Write Book Name");
                String book = s.nextLine();
                System.out.println("Write Author Name");
                String author = s.nextLine();
                library.addBook(book, author);
            }
            if (input.equals("i")){
                System.out.println("Write Book Name");
                String book = s.nextLine();
                System.out.println("Write Author Name");
                String author = s.nextLine();
                System.out.println("Write Your Name");
                String user = s.nextLine();
                library.issueBook(book, author, user);
            }
            if (input.equals("r")){
                System.out.println("Write Book Name");
                String book = s.nextLine();
                System.out.println("Write Author Name");
                String author = s.nextLine();
                System.out.println("Write Your Name");
                String user = s.nextLine();
                library.returnBook(book, author, user);
            }
            if (input.equals("e")){
                System.out.println("Thank You For Using our library System");
                System.out.println("Exiting....");
//                library.writeBookList();
//                library.writingIssuedList();
                return;
            }
        }

    }
}
