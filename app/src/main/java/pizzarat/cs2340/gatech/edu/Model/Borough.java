package pizzarat.cs2340.gatech.edu.Model;

import java.io.Serializable;

/**
 * @author Harrison Banh
 * This class represents the Borough of where the rat was sighted. Used to
 * create rat reports.
 */

public enum Borough implements Serializable{
    // Sectors of New York
    MANHATTAN("Manhattan"),
    STATEN_ISLAND("Staten Island"),
    QUEENS("Queens"),
    BROOKYLN("Brooklyn"),
    BRONX("Bronx");

    private final String sectorName;

    private Borough(String sectorName) {
        this.sectorName = sectorName;
    }

    /**
     * An accessor method to obtain a String version of the sector in New York
     * of the rat sighting.
     * @return the sector name as a String.
     */
    public String getSectorName() {
        return sectorName;
    }

}
