
public enum MemberType {
    STUDENT(3, "Student"),
    FACULTY(10, "Faculty"),
    COMMUNITY(5, "Community Member"),
    PREMIUM(7, "Premium Member");
    
    private final int maxLoanLimit;
    private final String displayName;
    
    MemberType(int maxLoanLimit, String displayName) {
        this.maxLoanLimit = maxLoanLimit;
        this.displayName = displayName;
    }
    
    public int getMaxLoanLimit() {
        return maxLoanLimit;
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    
    public boolean canBorrowMore(int currentLoans) {
        return currentLoans < maxLoanLimit;
    }
    
    
    public static MemberType fromDisplayName(String displayName) {
        for (MemberType type : values()) {
            if (type.displayName.equalsIgnoreCase(displayName)) {
                return type;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return displayName + " (Max Loans: " + maxLoanLimit + ")";
    }
}