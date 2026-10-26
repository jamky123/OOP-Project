public abstract class LibraryItem {

    private String itemId;
    private String title;
    private ItemStatus status;

    protected LibraryItem(String itemId, String title, ItemStatus status) {
        setItemId(itemId);
        setTitle(title);
        setStatus(status);
    }

    public String getItemId() {
        return itemId;
    }

    public String getTitle() {
        return title;
    }

    public ItemStatus getStatus() {
        return status;
    }

    public void setItemId(String itemId) {
        if (itemId != null && !itemId.isEmpty()) {
            this.itemId = itemId;
        }
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        }
    }

    public void setStatus(ItemStatus status) {
        if (status != null) {
            this.status = status;
        }
    }

    // Abstract methods - overridden differently by Book, Magazine, DVD
    public abstract int getLoanPeriod();

    public abstract String getItemType();

    @Override
    public String toString() {
        return String.format("[%s]\nTitle       : %s\nID          : %s\nStatus      : %s\nLoan Period : %d day(s)",
            getItemType(), title, itemId, status, getLoanPeriod());
    }
}