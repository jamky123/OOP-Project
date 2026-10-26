public class Book extends LibraryItem {

    private String author;
    private String isbn;

    public Book(String itemId, String title, String author, String isbn) {
        super(itemId, title, ItemStatus.AVAILABLE);
        setAuthor(author);
        setIsbn(isbn);
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        }
    }

    public void setIsbn(String isbn) {
        if (isbn != null && !isbn.isEmpty()) {
            this.isbn = isbn;
        }
    }

    @Override
    public int getLoanPeriod() {
        return 14;
    }

    @Override
    public String getItemType() {
        return "Book";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nAuthor      : %s\nISBN        : %s", author, isbn);
    }
}