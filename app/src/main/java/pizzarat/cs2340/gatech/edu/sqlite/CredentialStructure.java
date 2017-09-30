package pizzarat.cs2340.gatech.edu.sqlite;

/**
 * @author Evie Brown.
 */

class CredentialStructure {
    private String id;
    private String user;
    private String pass;
    private Boolean isAdmin;

    CredentialStructure(String user) {
        this(null, user, null, null);
    }

    CredentialStructure(String id, String user, String pass, Boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    //getters
    String getId() {
        return id;
    }

    String getUser() {
        return user;
    }

    String getPass() {
        if (pass == null)
            return "";
        System.out.println("777777777777777");
        return pass;
    }

    Boolean getAdmin() {
        return isAdmin;
    }

    //two sets of credentials are equal if they both have the same id
    @Override
    public boolean equals(Object obj) {
        return ((CredentialStructure) obj).getId() == id;
    }

    public boolean sameUser(CredentialStructure userTester) {
        return (userTester.getId() == user);
    }

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
