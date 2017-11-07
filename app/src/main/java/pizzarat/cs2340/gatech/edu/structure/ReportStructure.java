package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Harrison Banh
 *         This class is a container or holder for all the information related to making
 *         a rat sighting report.
 */
public class ReportStructure {
    private final String key;                    // Unique key assigned to each report
    private final String buildingType;               // Building type of the sighting
    private final String date;                   // May need to change later
    private final String time;                   // May need to change later
    private final String address;                // The address of the sighting
    private final String zipCode;                // The zip code of the rat report
    private final String city;                   // The city of rat report
    private final String borough;                // The borough of the rat sighting;
    private final String latitude;               // The latitude of the sighting
    private final String longitude;              // The longitude of the sighting

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
    public ReportStructure(String key, String buildingType, String date, String time,
                           String address, String zipCode, String city,
                           String borough, String latitude, String longitude) {
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

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the specified key for this rat sighting.
//     * @param key
//     */
//    public void setKey(String key) {
//        this.key = key;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Returns the latitude and longitude of the rat sighting.
     *
     * @return the latitude and longitude of the rat sighting
     */
    public String getBuildingType() {
        return buildingType;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the latitude and longitude of this rat sighting.
//     * @param buildingType the latitude and longitude of the sighting
//     */
//    public void setBuildingType(String buildingType) {
//        this.buildingType = buildingType;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Returns the date of the rat sighting report.
     *
     * @return the date of the rat sighting
     */
    public String getDate() {
        return date;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the date of this rat sighting report.
//     * @param date the specified date of the rat sighting
//     */
//    public void setDate(String date) {
//        this.date = date;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Gets the time of this rat sighting report.
     *
     * @return the time of the rat report
     */
    public String getTime() {
        return time;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the time of this rat report.
//     * @param time the specified time of the report
//     */
//    public void setTime(String time) {
//        this.time = time;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Gets the address of this rat sighting report.
     *
     * @return the address of this rat report
     */
    public String getAddress() {
        return address;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the address of this rat report to the specified address.
//     * @param address the specified address
//     */
//    public void setAddress(String address) {
//        this.address = address;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Gets the zip code of this rat report.
     *
     * @return the zip code of the rat report
     */
    public String getZipCode() {
        return zipCode;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the zip code of the rat report to the specified zip code.
//     * @param zipCode the specified zip code
//     */
//    public void setZipCode(String zipCode) {
//        this.zipCode = zipCode;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Gets the city of the buildingType of the rat report.
     *
     * @return the city of the rat report
     */
    public String getCity() {
        return city;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the city of the rat report to the specified city
//     * @param city the specified city
//     */
//    public void setCity(String city) {
//        this.city = city;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)

    /**
     * Gets the borough of the rat report
     *
     * @return the borough of the rat report
     */
    public String getBorough() {
        return borough;
    }

// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the borough of the rat report to the specified borough
//     * @param borough the specified borough
//     */
//    public void setBorough(String borough) {
//        this.borough = borough;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)


    /**
     * Returns the latitude of this rat sighting
     *
     * @return the latitude of the sighting
     */


    public String getLatitude() {
        return latitude;
    }


// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the latitude of the rat report to the specified latitude
//     * @param latitude the specified latitude
//     */
//
//    public void setLatitude(String latitude) {
//        this.latitude = latitude;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)


    /**
     * Returns the longitude of this rat sighting
     *
     * @return the longitude of the sighting
     */

    public String getLongitude() {
        return longitude;
    }


// --Commented out by Inspection START (11/6/2017 1:48 AM):
//    /**
//     * Sets the longitude of the rat report to the specified latitude
//     * @param longitude the specified latitude
//     */
//
//
//    public void setLongitude(String longitude) {
//        this.longitude = longitude;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:48 AM)


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
