public class Address {
    private String roadName;
    private String city;
    private String state;
    private String country;

    public Address(String roadName, String city, String state, String country) {
        this.roadName = roadName;
        this.city = city;
        this.state = state;
        this.country = country;
    }
    public String getFullAddress() {
        return roadName + ", " + city + ", " + state + ", " + country;
    }
}
