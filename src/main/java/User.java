public class User {

    private int id;
    private String name;
    private java.sql.Timestamp creationDate;

    public User(String name, java.sql.Timestamp creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public User(int id, String name, java.sql.Timestamp creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    // getValues methods
    public String getName () {
        return this.name;
    }
    public java.sql.Timestamp getCreationDate () {
        return this.creationDate;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', creationDate=" + creationDate + "}";
    }
}
