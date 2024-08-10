public class Ticket {
    private int id;
    private int userId;
    private String ticketType;
    private java.sql.Timestamp creationDate;

    public Ticket(int userId, String ticketType, java.sql.Timestamp creationDate) {
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = creationDate;
    }

    public Ticket(int id, int userId, String ticketType, java.sql.Timestamp creationDate) {
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
    public java.sql.Timestamp getCreationDate() {
        return this.creationDate;
    }

    @Override
    public String toString() {
        return "Ticket{id=" + id + ", userID='" + userId + "', ticketType=" + ticketType + ", creationDate=" + creationDate +"}";
    }
}
