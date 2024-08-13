import java.time.LocalDateTime;

public class User {

    private int id;
    private String name;
    private LocalDateTime creationDate;

    public User(String name, LocalDateTime creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }

    public User(int id, String name, LocalDateTime creationDate) {
        this.id = id;
        this.name = name;
        this.creationDate = creationDate;
    }

    // getValues methods
    public String getName () {
        return this.name;
    }
    public LocalDateTime getCreationDate () {
        return this.creationDate;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "', creationDate=" + creationDate + "}";
    }
}
