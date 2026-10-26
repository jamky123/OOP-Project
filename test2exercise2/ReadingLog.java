public class ReadingLog {
    private String readingType;
    private double pages;
    public ReadingLog(String readingType, double pages) {
        this.readingType = readingType;
        this.pages = pages;
    }
    public String getReadingType() {
        return readingType;
    }
    public double getPages() {
        return pages;
    }
}
