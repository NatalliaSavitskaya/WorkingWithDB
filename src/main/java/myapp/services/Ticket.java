package myapp.services;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tickets")

public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "ticket_type")
    private String ticketType;

    public Ticket() {}

    public Ticket(int userId, String ticketType, LocalDateTime creationDate) {
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    // getValues methods
    public int getUserId() {
        return this.userId;
    }
    public String getTicketType() {
        return this.ticketType;
    }
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    // setvalues methods
    public void setTicketType(String ticketType) { this.ticketType = ticketType; }

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", userID='" + userId + "', ticketType=" + ticketType + ", creationDate=" +
                creationDate +"}";
    }
}