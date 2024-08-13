import java.time.LocalDateTime;

public class Ticket {
    private int id;
    private int userId;
    private String ticketType;
    private LocalDateTime creationDate;

    public Ticket(int userId, String ticketType, LocalDateTime creationDate) {
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public Ticket(int id, int userId, String ticketType, LocalDateTime creationDate) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", userID='" + userId + "', ticketType=" + ticketType + ", creationDate=" + creationDate +"}";
    }
}
