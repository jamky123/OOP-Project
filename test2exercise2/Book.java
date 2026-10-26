import java.util.*;

public class Book {
    private String bookId;
    private String borrowedBy;
    private double totalPagesRead;
    private String genre;
    private ArrayList<ReadingLog> readingLogs;
    public Book (String bookId, String genre){
        this.bookId = bookId;
        this.genre = genre;
        this.borrowedBy = "none";
        this.totalPagesRead = 0.0;
        this.readingLogs = new ArrayList<ReadingLog>();
    }
    public String getBookId() {
        return bookId;
    }
    public String getGenre() {
        return genre;
    }
    public double getTotalPagesRead() {
        return totalPagesRead;
    }
    public void addReadingLog(String type, double pages) {
        readingLogs.add(new ReadingLog(type, pages));
        totalPagesRead += pages;
    }
    public void setBorrowedBy(String memberName) {
        this.borrowedBy = memberName;
    }
    public void removeReadingLog(String type, double pages) {
    for (int i = 0; i < readingLogs.size(); i++) {
        ReadingLog log = readingLogs.get(i);
        
        if (log.getReadingType().equals(type)) { // cek pake .equals
            readingLogs.remove(i);          // hapus dari list
            totalPagesRead -= pages;       // kurangin total pake -=
            System.out.println("Removed log: " + type + " with " + pages + " pages");
            break; // udah ketemu 1, stop. Biar ndk hapus semua yg namanya sama
        }
    }
    }
    public void printAllInfo(){
        System.out.println("Book ID: " + bookId);
        System.out.println("Genre: " + genre);
        System.out.println("Borrowed By: " + borrowedBy);
        System.out.println("Total Pages Read: " + totalPagesRead);
        System.out.println("Reading Logs:");
        for (ReadingLog log : readingLogs) {
            System.out.println("- " + log.getReadingType() + ": " + log.getPages() + " pages");
        }
    }
    
}
