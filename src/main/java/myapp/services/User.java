package myapp.services;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Users")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "name")
    private String name;

    public User () {}

    public User(String name, LocalDateTime creationDate) {
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

    // setValues methods
    public void setId (int id) {
        this.id = id;
    }
    public void setName (String name) {
        this.name = name;
    }
    public void setCreationDate (LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "User name='" + name + "', creationDate=" + creationDate;
    }
}