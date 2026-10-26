import java.util.*;
public class Member {
    private String memberID;
    private String name;
    private Address address;
    private ArrayList<Book> borrowedBooks;
    public Member(String memberID, String name, Address address) {
        this.memberID = memberID;
        this.name = name;
        this.address = address;
        this.borrowedBooks = new ArrayList<Book>();
    }
    public String getMemberID() {
        return memberID;
    }
    public String getName() {
        return name;
    }
    public String getAddress(){
        return address.getFullAddress();
    }
    public void borrowBook(Book book, Library library){
        borrowedBooks.add(book);
        book.setBorrowedBy(name);
    }
    public void returnBook(Book book, Library library){
        borrowedBooks.remove(book);
        book.setBorrowedBy("none");
    }
    public void printAllInfo() {
        System.out.println("Member ID: " + memberID);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address.getFullAddress());
        System.out.println("Borrowed Books:");
        for (Book book : borrowedBooks) {
            System.out.println("- " + book.getBookId() + " (" + book.getGenre() + ")");
        }
    }

}