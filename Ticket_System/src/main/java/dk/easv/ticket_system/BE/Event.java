package dk.easv.ticket_system.BE;



import java.sql.Date;
import java.time.LocalTime;

public class Event {
    private int eventID;
    private String eventTitle;
    private String eventDescription;
    private String location;



    private Date eventStartDate;
    private Date eventEndDate;
    private String eventStartTime;
    private String eventEndTime;
    private String recTransport;

    public Event(int eventID, String eventTitle, Date eventStartDate, String location,
                 String eventStartTime, String eventEndTime, String eventDescription, String recTransport) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
        this.recTransport = recTransport;
    }

    public Event(String eventTitle, Date eventStartDate, String location,
                 String eventStartTime, String eventEndTime, String eventDescription, String recTransport) {

        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
        this.recTransport = recTransport;
    }

    public Event(String eventTitle, Date eventStartDate, String location,
                 String eventStartTime, String eventEndTime, String recTransport) {
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.recTransport = recTransport;

    }

    public Event(String eventTitle, String eventDescription, String eventLocation, Date eventStartDate, Date endDate, String startTime, String endTime) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.location = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = endDate;
        this.eventStartTime = startTime;
        this.eventEndTime = endTime;

    }

    public Event(int eventID, String eventTitle, Date eventStartDate, String location, String eventStartTime, String eventEndTime, String eventDescription) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
    }


    public int getEventID() {
        return eventID;
    }


    public String geteventTitle() {
        return eventTitle;
    }

    public void seteventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    public Date geteventStartDate() {
        return eventStartDate;
    }

    public void seteventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public   String geteventStartTime() {
        return eventStartTime;
    }

    public void seteventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public String geteventEndTime() {
        return eventEndTime;
    }

    public void seteventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public String geteventDescription() {
        return eventDescription;
    }

    public void seteventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getRecTransport() {
        return recTransport;
    }

    public void setRecTransport(String recTransport) {
        this.recTransport = recTransport;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }
}
