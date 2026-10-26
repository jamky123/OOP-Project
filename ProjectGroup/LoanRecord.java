import java.time.LocalDate;

public class LoanRecord {

    private Member member;
    private LibraryItem item;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    public LoanRecord(Member member, LibraryItem item, LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate) {
        this.member = member;
        this.item = item;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
    }

    /**
     * @return true if the item has been returned (returnDate is set)
     */
    public boolean isReturned() {
        return returnDate != null;
    }

    /**
     * @return true if the item has not been returned and the due date has passed
     */
    public boolean isOverdue() {
        if (isReturned()) {
            return false;
        }
        return LocalDate.now().isAfter(dueDate);
    }

    // Getters and setters

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public LibraryItem getItem() {
        return item;
    }

    public void setItem(LibraryItem item) {
        this.item = item;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "LoanRecord{" +
                "member=" + member.getName() +
                ", item=" + item.getTitle() +
                ", borrowDate=" + borrowDate +
                ", dueDate=" + dueDate +
                ", returnDate=" + (returnDate != null ? returnDate : "Not returned") +
                ", overdue=" + isOverdue() +
                '}';
    }
}