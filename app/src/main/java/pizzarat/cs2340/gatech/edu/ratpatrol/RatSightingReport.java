package pizzarat.cs2340.gatech.edu.ratpatrol;

/**
 * @author Harrison Banh
 * This class is a container or holder for all the information related to making
 * a rat sighting report.
 */
public class RatSightingReport {
    private int key;                    // Unique key assigned to each report
    private String location;            // Latitude and longitude of the sighting
    private String date;                // May need to change later
    private int time;                   // May need to change later
    private String address;             // The address of the sighting
    private int zipCode;                // The zip code of the rat report
    private String city;                // The city of rat report
    private Borough borough;            // The borough of the rat sighting;

    /**
     * Creates the most specific rat sighting report by specifying all fields.
     * @param key the unique key assigned to each report
     * @param location the latitude and longitude of the sighting
     * @param date the date of the sighting
     * @param time the time of the sighting
     * @param address the address of the sighting
     * @param zipCode the zip code portion of the address of the sighting
     * @param city the city portion of the address of the sighting
     * @param borough the borough in New York where the sighting occurred
     */
    public RatSightingReport(int key, String location, String date, int time,
                             String address, int zipCode, String city,
                             Borough borough) {
        this.key = key;
        this.location = location;
        this.date = date;
        this.time = time;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.borough = borough;
    }

    /**
     * Returns unique key of the rat sighting.
     * @return the unique key of the sighting
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the specified key for this rat sighting.
     * @param key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Returns the latitude and longitude of the rat sighting.
     * @return the latitude and longitude of the rat sighting
     */
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Borough getBorough() {
        return borough;
    }

    public void setBorough(Borough borough) {
        this.borough = borough;
    }
}
