import java.util.*;

public class Library {
    private String libraryName;
    private Address address;
    private ArrayList<Book> books;
    public Library(String libraryName, Address address) {
        this.libraryName = libraryName;
        this.address = address;
        this.books = new ArrayList<Book>();
    }
    public String getLibraryName() {
        return libraryName;
    }
    public String getAddress() {
        return address.getFullAddress();
    }
    public void addBook(Book book) {
        books.add(book);
    }
    public void removeBook(Book book) {
        books.remove(book);
    }
    public void printAllInfo() {
        System.out.println("Library Name: " + libraryName);
        System.out.println("Address: " + address.getFullAddress());
        System.out.println("Books in Library:");
        for (Book book : books) {
            book.printAllInfo();
            System.out.println();
        }
    }
}
