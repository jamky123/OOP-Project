import java.util.ArrayList;
import java.util.List;


public class Member {
    private String memberId;
    private String name;
    private MemberType memberType;
    private List<String> borrowedItemIds; 
    private int totalItemsBorrowed; 
    
   
    public Member(String memberId, String name, MemberType memberType) {
        this.memberId = memberId;
        this.name = name;
        this.memberType = memberType;
        this.borrowedItemIds = new ArrayList<>();
        this.totalItemsBorrowed = 0;
    }
    
    // ========== Getters ==========
    public String getMemberId() { 
        return memberId; 
    }
    
    public String getName() { 
        return name; 
    }
    
    public MemberType getMemberType() { 
        return memberType; 
    }
    
    public int getTotalItemsBorrowed() { 
        return totalItemsBorrowed; 
    }
    
    public List<String> getBorrowedItemIds() {
        return new ArrayList<>(borrowedItemIds); }
    
    // ========== Setters with Validation ==========
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name.trim();
    }
    
    public void setMemberType(MemberType memberType) {
        if (memberType == null) {
            throw new IllegalArgumentException("Member type cannot be null");
        }
        this.memberType = memberType;
    }
    
    // ========== Business Methods ==========
    
    
    public boolean borrowItem(String itemId) {
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be null or empty");
        }
        
        if (!memberType.canBorrowMore(totalItemsBorrowed)) {
            return false; // Member has reached their borrowing limit
        }
        
        if (borrowedItemIds.contains(itemId)) {
            return false; // Already borrowed this item
        }
        
        borrowedItemIds.add(itemId);
        totalItemsBorrowed++;
        return true;
    }
    
    
    public boolean returnItem(String itemId) {
        if (itemId == null || itemId.trim().isEmpty()) {
            throw new IllegalArgumentException("Item ID cannot be null or empty");
        }
        
        if (borrowedItemIds.remove(itemId)) {
            totalItemsBorrowed--;
            return true;
        }
        return false;
    }
    
    
    public boolean hasBorrowedItem(String itemId) {
        return borrowedItemIds.contains(itemId);
    }
    
   
    public boolean canBorrowMore() {
        return memberType.canBorrowMore(totalItemsBorrowed);
    }
    
   
    public int getRemainingBorrowingCapacity() {
        return memberType.getMaxLoanLimit() - totalItemsBorrowed;
    }
    
    
    public String getBorrowingStatus() {
        return String.format(
            "Member: %s (%s)\n" +
            "Current Borrowed: %d/%d items\n" +
            "Remaining Capacity: %d",
            name, memberType.getDisplayName(),
            totalItemsBorrowed, memberType.getMaxLoanLimit(),
            getRemainingBorrowingCapacity()
        );
    }
    
    @Override
    public String toString() {
        return String.format("Member[ID: %s, Name: %s, Type: %s, Borrowed: %d]",
            memberId, name, memberType.getDisplayName(), totalItemsBorrowed);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Member member = (Member) obj;
        return memberId.equals(member.memberId);
    }
    
    @Override
    public int hashCode() {
        return memberId.hashCode();
    }
}