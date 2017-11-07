package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Evie Brown.
 */

public class CredentialStructure {
    private final String id;                      // User number
    private final String user;                    // User's username
    private final String pass;                    // User's password
    private final Boolean isAdmin;                // Admin privileges

// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    CredentialStructure(String user) {
//        this(null, user, null, null);
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

    /**
     * Creates an object representing the user's credentials to be stored in
     * the SQL database
     *
     * @param id      a designated user id to reference users in the database
     * @param user    the user's username
     * @param pass    the user's password
     * @param isAdmin a parameter representing if the user has admin privileges
     */
    public CredentialStructure(String id, String user, String pass,
                               Boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    /**
     * Returns the user of this CredentialStructure
     *
     * @return the user
     */
    private String getId() {
        return id;
    }

    /**
     * Returns the user's username
     * @return the user's username
     */
    public String getUser() {
        return user;
    }

    /**
     * Returns the password of the user
     * @return the user's password
     */
    public String getPass() {
        if (pass == null)
            return "";
        return pass;
    }

// --Commented out by Inspection START (11/6/2017 1:51 AM):
//    /**
//     * Returns a value determining if the user has admin privileges
//     * @return true if the user has admin privileges
//     */
//    public Boolean getAdmin() {
//        return isAdmin;
//    }
// --Commented out by Inspection STOP (11/6/2017 1:51 AM)

    /**
     * Determines if two users are equal. Returns true if two users have the
     * same id number.
     */
    @Override
    public boolean equals(Object obj) {
        return ((CredentialStructure) obj).getId().equals(id);
    }


// --Commented out by Inspection START (11/6/2017 1:49 AM):
//    public boolean sameUser(CredentialStructure userTester) {
//        return (userTester.getId().equals(user));
//    }
// --Commented out by Inspection STOP (11/6/2017 1:49 AM)

    /**
     * Returns a String representation of the this CredentialStructure
     * @return a description of this CredentialStructure.
     */
    @Override
    public String toString() {
        return "CredentialStructure{" +
                "id='" + id + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
