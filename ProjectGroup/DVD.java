public class DVD extends LibraryItem {

    private int durationMinutes;
    private String director;

    public DVD(String itemId, String title, int durationMinutes, String director) {
        super(itemId, title, ItemStatus.AVAILABLE);
        setDurationMinutes(durationMinutes);
        setDirector(director);
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public String getDirector() {
        return director;
    }

    public void setDurationMinutes(int durationMinutes) {
        if (durationMinutes > 0) {
            this.durationMinutes = durationMinutes;
        }
    }

    public void setDirector(String director) {
        if (director != null && !director.isEmpty()) {
            this.director = director;
        }
    }

    @Override
    public int getLoanPeriod() {
        return 3;
    }

    @Override
    public String getItemType() {
        return "DVD";
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nDuration    : %d min\nDirector    : %s",
            durationMinutes, director);
    }
}