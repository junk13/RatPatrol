package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Harrison Banh
 *         This class is a container or holder for all the information related
 *         to making a rat sighting report.
 */
public class ReportStructure {
    private final String key;             // Unique key assigned to each report
    private final String buildingType;    // Building type of the sighting
    private final String date;            // May need to change later
    private final String time;            // May need to change later
    private final String address;         // The address of the sighting
    private final String zipCode;         // The zip code of the rat report
    private final String city;            // The city of rat report
    private final String borough;         // The borough of the rat sighting;
    private final String latitude;        // The latitude of the sighting
    private final String longitude;       // The longitude of the sighting

    /**
     * Creates the most specific rat sighting report by specifying all fields.
     *
     * @param key          the unique key assigned to each report
     * @param buildingType the latitude and longitude of the sighting
     * @param date         the date of the sighting
     * @param time         the time of the sighting
     * @param address      the address of the sighting
     * @param zipCode      the zip code portion of the address of the sighting
     * @param city         the city portion of the address of the sighting
     * @param borough      the borough where the sighting occurred
     * @param latitude     the latitude of the rat sighting
     * @param longitude    the longitude of the rat sighting
     */
    public ReportStructure(String key, String buildingType, String date,
                           String time, String address, String zipCode,
                           String city, String borough, String latitude,
                           String longitude) {
        this.key = key;
        this.buildingType = buildingType;
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
     *
     * @return the unique key of the sighting
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the latitude and longitude of the rat sighting.
     *
     * @return the latitude and longitude of the rat sighting
     */
    public String getBuildingType() {
        return buildingType;
    }

    /**
     * Returns the date of the rat sighting report.
     *
     * @return the date of the rat sighting
     */
    public String getDate() {
        return date;
    }

    /**
     * Gets the time of this rat sighting report.
     *
     * @return the time of the rat report
     */
    public String getTime() {
        return time;
    }

    /**
     * Gets the address of this rat sighting report.
     *
     * @return the address of this rat report
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the zip code of this rat report.
     *
     * @return the zip code of the rat report
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * Gets the city of the buildingType of the rat report.
     *
     * @return the city of the rat report
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the borough of the rat report
     *
     * @return the borough of the rat report
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Returns the latitude of this rat sighting
     *
     * @return the latitude of the sighting
     */


    public String getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of this rat sighting
     *
     * @return the longitude of the sighting
     */

    public String getLongitude() {
        return longitude;
    }

    /**
     * Returns a mini description of the rat report to be used to display
     * information when the marker of this report is clicked in the Google Map.
     *
     * @return a mini description of this report
     */
    public String mapToString() {
        return "Date: " + getDate() + "\n"
                + "Time: " + getTime() + "\n"
                + "Address: " + getAddress() + "\n"
                + "Location: " + getBuildingType();
    }

}
