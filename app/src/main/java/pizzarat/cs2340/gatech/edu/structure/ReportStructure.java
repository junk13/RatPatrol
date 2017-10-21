package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Harrison Banh
 * This class is a container or holder for all the information related to making
 * a rat sighting report.
 */
public class ReportStructure {
    private String key;                    // Unique key assigned to each report
    private String location;               // Building type of the sighting
    private String date;                   // May need to change later
    private String time;                   // May need to change later
    private String address;                // The address of the sighting
    private String zipCode;                // The zip code of the rat report
    private String city;                   // The city of rat report
    private String borough;                // The borough of the rat sighting;
    private String latitude;               // The latitude of the sighting
    private String longitude;              // The longitude of the sighting
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

    // TODO fix constructor for lat and long
    public ReportStructure(String key, String location, String date, String time,
                           String address, String zipCode, String city,
                           String borough, String latitude, String longitude) {
        this.key = key;
        this.location = location;
        this.date = date;
        this.time = time;
        this.address = address;
        this.zipCode = zipCode;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns unique key of the rat sighting.
     * @return the unique key of the sighting
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the specified key for this rat sighting.
     * @param key
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Returns the latitude and longitude of the rat sighting.
     * @return the latitude and longitude of the rat sighting
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the latitude and longitude of this rat sighting.
     * @param location the latitude and longitude of the sighting
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Returns the date of the rat sighting report.
     * @return the date of the rat sighting
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the date of this rat sighting report.
     * @param date the specified date of the rat sighting
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets the time of this rat sighting report.
     * @return the time of the rat report
     */
    public String getTime() {
        return time;
    }

    /**
     * Sets the time of this rat report.
     * @param time the specified time of the report
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * Gets the address of this rat sighting report.
     * @return the address of this rat report
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of this rat report to the specified address.
     * @param address the specified address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the zip code of this rat report.
     * @return the zip code of the rat report
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Sets the zip code of the rat report to the specified zip code.
     * @param zipCode the specified zip code
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the city of the location of the rat report.
     * @return the city of the rat report
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the rat report to the specified city
     * @param city the specified city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the borough of the rat report
     * @return the borough of the rat report
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Sets the borough of the rat report to the specified borough
     * @param borough the specified borough
     */
    public void setBorough(String borough) {
        this.borough = borough;
    }


    /**
     * Returns the latitude of this rat sighting
     * @return the latitude of the sighting
     */


    public String getLatitude() {
        return latitude;
    }


    /**
     * Sets the latitude of the rat report to the specified latitude
     * @param latitude the specified latitude
     */

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }


    /**
     * Returns the longitude of this rat sighting
     * @return the longitude of the sighting
     */

    public String getLongitude() {
        return longitude;
    }


    /**
     * Sets the longitude of the rat report to the specified latitude
     * @param longitude the specified latitude
     */


    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }


    /**
     * Returns a mini description of the rat report to be used to display
     * information when the marker of this report is clicked in the Google Map.
     * @return a mini description of this report
     */
    public String mapToString() {
        return "Date: " + getDate() + "\n"
                + "Time: " + getTime() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Location: " + getLocation();
    }

}
