package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Evie Brown.
 */

public class CredentialStructure {
    private final String id;                      // User number
    private final String user;                    // User's username
    private final String pass;                    // User's password
    private final Boolean isAdmin;                // Admin privileges

    CredentialStructure(String user) {
        this(null, user, null, null);
    }

    public CredentialStructure(String id, String user, String pass, Boolean isAdmin) {
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
    public String getId() {
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
        System.out.println("777777777777777");
        return pass;
    }

    /**
     * Returns a value determining if the user has admin privileges
     * @return true if the user has admin privileges
     */
    public Boolean getAdmin() {
        return isAdmin;
    }

    /**
     * Determines if two users are equal. Returns true if two users have the
     * same id number.
     */
    @Override
    public boolean equals(Object obj) {
        return ((CredentialStructure) obj).getId().equals(id);
    }


    public boolean sameUser(CredentialStructure userTester) {
        return (userTester.getId().equals(user));
    }

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
