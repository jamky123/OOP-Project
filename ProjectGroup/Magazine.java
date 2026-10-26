public class Magazine extends LibraryItem {

    private int issueNumber;
    private String publicationMonth;

    public Magazine(String itemId, String title, int issueNumber, String publicationMonth) {
        super(itemId, title, ItemStatus.AVAILABLE);
        setIssueNumber(issueNumber);
        setPublicationMonth(publicationMonth);
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public String getPublicationMonth() {
        return publicationMonth;
    }

    public void setIssueNumber(int issueNumber) {
        if (issueNumber > 0) {
            this.issueNumber = issueNumber;
        }
    }

    public void setPublicationMonth(String publicationMonth) {
        if (publicationMonth != null && !publicationMonth.isEmpty()) {
            this.publicationMonth = publicationMonth;
        }
    }

    @Override
    public int getLoanPeriod() {
        return 7;
    }

    @Override
    public String getItemType() {
        return "Magazine";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nIssue No    : %d\nPublished   : %s",
            issueNumber, publicationMonth);
    }
}