package dk.easv.ticket_system.BE;



import java.sql.Date;
import java.time.LocalTime;

public class Event {
    private int eventID;
    private String eventName;
    private Date eventDate;
    private String location;
    private LocalTime eventStart;
    private LocalTime eventEnd;
    private String eDescription;
    private String recTransport;

    public Event(int eventID, String eventName, Date eventDate, String location,
                 LocalTime eventStart, LocalTime eventEnd, String eDescription, String recTransport) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eDescription = eDescription;
        this.recTransport = recTransport;
    }

    public Event(String eventName, Date eventDate, String location,
                 LocalTime eventStart, LocalTime eventEnd, String eDescription, String recTransport) {

        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.eDescription = eDescription;
        this.recTransport = recTransport;
    }

    public Event(String eventName, Date eventDate, String location,
                 LocalTime eventStart, LocalTime eventEnd, String recTransport) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.location = location;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.recTransport = recTransport;

    }




    public int getEventID() {
        return eventID;
    }


    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public   LocalTime getEventStart() {
        return eventStart;
    }
    public void setEventStart(LocalTime eventStart) {
        this.eventStart = eventStart;
    }

    public LocalTime getEventEnd() {
        return eventEnd;
    }

    public void setEventEnd(LocalTime eventEnd) {
        this.eventEnd = eventEnd;
    }

    public String geteDescription() {
        return eDescription;
    }

    public void seteDescription(String eDescription) {
        this.eDescription = eDescription;
    }

    public String getRecTransport() {
        return recTransport;
    }

    public void setRecTransport(String recTransport) {
        this.recTransport = recTransport;
    }
}
