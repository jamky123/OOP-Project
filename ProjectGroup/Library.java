import java.util.ArrayList;
import java.time.LocalDate;

public class Library {
    private ArrayList<LibraryItem> catalog;    
    private ArrayList<Member> members;         
    private ArrayList<LoanRecord> loanRecords;
    private LibraryItem[] featuredItems;
    
    public Library() {
        catalog = new ArrayList<>();
        members = new ArrayList<>();
        loanRecords = new ArrayList<>();
        featuredItems = new LibraryItem[5];
    }

    //ITEM METHODS 
    public void addItem(LibraryItem item) {
        if (item != null) {
            catalog.add(item);
        }
    }

    public void removeItem(String itemId) throws ItemUnavailableException {
        LibraryItem item = searchItemById(itemId);
        if (item == null) throw new ItemUnavailableException("Item not found");
        catalog.remove(item);
    }

    public LibraryItem searchItemById(String itemId) {
        for (LibraryItem item : catalog) {
            if (item.getItemId().equals(itemId)) return item;
        }
        return null;
    }

    public ArrayList<LibraryItem> searchItemsByTitle(String title) {
        ArrayList<LibraryItem> results = new ArrayList<>();
        for (LibraryItem item : catalog) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }

    // methods for specific item types
    public void addBook(String id, String title, String author, String isbn) {
        addItem(new Book(id, title, author, isbn));
    }

    public void addMagazine(String id, String title, int issueNumber, String publicationMonth) {
        addItem(new Magazine(id, title, issueNumber, publicationMonth));
    }

    public void addDVD(String id, String title, int duration, String director) {
        addItem(new DVD(id, title, duration, director));
    }

    //MEMBER METHODS 
    public void addMember(String memberId, String name, MemberType memberType) {
        members.add(new Member(memberId, name, memberType));
    }

    public void removeMember(String memberId) throws ItemUnavailableException {
        Member member = searchMemberById(memberId);
        if (member == null) throw new ItemUnavailableException("Member not found");
        members.remove(member);
    }

    public Member searchMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) return member;
        }
        return null;
    }

    // LOAN & RETURN METHODS 
    public void borrowItem(String memberId, String itemId) throws MaxLoanExceededException, ItemUnavailableException {
        Member member = searchMemberById(memberId);
        LibraryItem item = searchItemById(itemId);

        if (member == null) throw new ItemUnavailableException("Member not found");
        if (item == null) throw new ItemUnavailableException("Item not found");
        if (item.getStatus() != ItemStatus.AVAILABLE) throw new ItemUnavailableException("Item is not available");
        if (!member.canBorrowMore()) throw new MaxLoanExceededException("Max loan limit reached");

        item.setStatus(ItemStatus.BORROWED);
        member.borrowItem(itemId);
        LocalDate borrowDate = LocalDate.now();
        LocalDate dueDate = borrowDate.plusDays(item.getLoanPeriod());
        LoanRecord record = new LoanRecord(member, item, borrowDate, dueDate, null);
        loanRecords.add(record);
    }

    public void returnItem(String memberId, String itemId) throws ItemUnavailableException {
        Member member = searchMemberById(memberId);
        LibraryItem item = searchItemById(itemId);

        if (member == null) throw new ItemUnavailableException("Member not found");
        if (item == null) throw new ItemUnavailableException("Item not found");

        item.setStatus(ItemStatus.AVAILABLE);
        member.returnItem(itemId);

        for (LoanRecord record : loanRecords) {
            if (record.getMember().getMemberId().equals(memberId) 
                && record.getItem().getItemId().equals(itemId) 
                && record.getReturnDate() == null) {
                record.setReturnDate(LocalDate.now());
                break;
            }
        }
    }

    public String listAllItems() {
        if (catalog.isEmpty()) return "No items in catalog";
        StringBuilder result = new StringBuilder("=== CATALOG ===\n");
        for (LibraryItem item : catalog) {
            result.append(item.toString()).append("\n");
        }
        return result.toString();
    }

    public String listAllMembers() {
        if (members.isEmpty()) return "No members";
        StringBuilder result = new StringBuilder("=== MEMBERS ===\n");
        for (Member member : members) {
            result.append(member.toString()).append("\n");
        }
        return result.toString();
    }

    public void addFeaturedItem(LibraryItem item) throws ItemUnavailableException {
        if (item == null) throw new ItemUnavailableException("Item cannot be null");
        
        for (int i = 0; i < featuredItems.length; i++) {
            if (featuredItems[i] == null) {
                featuredItems[i] = item;
                return;
            }
        }
        throw new ItemUnavailableException("Featured items list is full (max 5)");
    }

    public String listFeaturedItems() {
        StringBuilder result = new StringBuilder("=== FEATURED ITEMS ===\n");
        boolean hasFeatured = false;
        for (LibraryItem item : featuredItems) {
            if (item != null) {
                result.append(item.toString()).append("\n");
                hasFeatured = true;
            }
        }
        return hasFeatured ? result.toString() : "No featured items";
    }

    public String listLoanRecords() { 
        if (loanRecords.isEmpty()) return "No loan records";
        StringBuilder result = new StringBuilder("=== LOAN HISTORY ===\n");
        for (LoanRecord record : loanRecords) {
            result.append(record.toString()).append("\n");
        }
        return result.toString();
    }
}