package pizzarat.cs2340.gatech.edu.structure;

/**
 * @author Evie Brown.
 */

public class CredentialStructure {
    private String id;
    private String user;
    private String pass;
    private Boolean isAdmin;

    CredentialStructure(String user) {
        this(null, user, null, null);
    }

    public CredentialStructure(String id, String user, String pass, Boolean isAdmin) {
        this.id = id;
        this.user = user;
        this.pass = pass;
        this.isAdmin = isAdmin;
    }

    //getters
    public String getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        if (pass == null)
            return "";
        System.out.println("777777777777777");
        return pass;
    }

    public Boolean getAdmin() {
        return isAdmin;
    }

    //two sets of credentials are equal if they both have the same id
    @Override
    public boolean equals(Object obj) {
        return ((CredentialStructure) obj).getId().equals(id);
    }

    public boolean sameUser(CredentialStructure userTester) {
        return (userTester.getId().equals(user));
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
