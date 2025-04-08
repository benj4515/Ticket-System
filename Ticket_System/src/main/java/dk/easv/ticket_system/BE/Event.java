/**
 * Represents an event in the ticket system.
 * Contains information about the event such as title, description, location, dates, times,
 * and recommended transportation.
 */
package dk.easv.ticket_system.BE;

import java.sql.Date;
import java.time.LocalTime;

public class Event {
    private int eventID;                // Unique identifier for the event
    private String eventTitle;          // Title of the event
    private String eventDescription;    // Description of the event
    private String location;            // Location where the event is held
    private Date eventStartDate;        // Start date of the event
    private Date eventEndDate;          // End date of the event (for multi-day events)
    private String eventStartTime;      // Start time of the event
    private String eventEndTime;        // End time of the event
    private String recTransport;        // Recommended transportation information
    private int generatedEventID;       // Generated identifier for the event (may be used for temporary IDs before DB persistence)

    /**
     * Constructs a complete event with all details including an ID and recommended transportation.
     *
     * @param eventID Unique identifier for the event
     * @param eventTitle Title of the event
     * @param eventStartDate Start date of the event
     * @param location Location where the event is held
     * @param eventStartTime Start time of the event
     * @param eventEndTime End time of the event
     * @param eventDescription Description of the event
     * @param recTransport Recommended transportation information
     */
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

    /**
     * Constructs an event without an ID (for new events before database insertion) but with transportation info.
     *
     * @param eventTitle Title of the event
     * @param eventStartDate Start date of the event
     * @param location Location where the event is held
     * @param eventStartTime Start time of the event
     * @param eventEndTime End time of the event
     * @param eventDescription Description of the event
     * @param recTransport Recommended transportation information
     */
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

    /**
     * Constructs an event with minimal required information and transportation info but no description.
     *
     * @param eventTitle Title of the event
     * @param eventStartDate Start date of the event
     * @param location Location where the event is held
     * @param eventStartTime Start time of the event
     * @param eventEndTime End time of the event
     * @param recTransport Recommended transportation information
     */
    public Event(String eventTitle, Date eventStartDate, String location,
                 String eventStartTime, String eventEndTime, String recTransport) {
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.recTransport = recTransport;
    }

    /**
     * Constructs an event with start and end dates but no transportation info.
     *
     * @param eventTitle Title of the event
     * @param eventDescription Description of the event
     * @param eventLocation Location where the event is held
     * @param eventStartDate Start date of the event
     * @param endDate End date of the event
     * @param startTime Start time of the event
     * @param endTime End time of the event
     */
    public Event(String eventTitle, String eventDescription, String eventLocation, Date eventStartDate, Date endDate, String startTime, String endTime) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.location = eventLocation;
        this.eventStartDate = eventStartDate;
        this.eventEndDate = endDate;
        this.eventStartTime = startTime;
        this.eventEndTime = endTime;
    }

    /**
     * Constructs an event with ID but no end date or transportation info.
     *
     * @param eventID Unique identifier for the event
     * @param eventTitle Title of the event
     * @param eventStartDate Start date of the event
     * @param location Location where the event is held
     * @param eventStartTime Start time of the event
     * @param eventEndTime End time of the event
     * @param eventDescription Description of the event
     */
    public Event(int eventID, String eventTitle, Date eventStartDate, String location, String eventStartTime, String eventEndTime, String eventDescription) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventDescription = eventDescription;
    }

    /**
     * Constructs an event with no ID but with end date, often used when creating a new event.
     *
     * @param eventTitle Title of the event
     * @param eventDescription Description of the event
     * @param eventLocation Location where the event is held
     * @param startDate Start date of the event
     * @param startTime Start time of the event
     * @param endTime End time of the event
     * @param eventEndDate End date of the event
     */
    public Event(String eventTitle, String eventDescription, String eventLocation, Date startDate, String startTime, String endTime, Date eventEndDate) {
        this.eventTitle = eventTitle;
        this.eventDescription = eventDescription;
        this.location = eventLocation;
        this.eventStartDate = startDate;
        this.eventStartTime = startTime;
        this.eventEndTime = endTime;
        this.eventEndDate = eventEndDate;
    }

    /**
     * Constructs an event with ID and end date, typically used when updating an existing event.
     *
     * @param eventID Unique identifier for the event
     * @param eventTitle Title of the event
     * @param eventStartDate Start date of the event
     * @param location Location where the event is held
     * @param eventDescription Description of the event
     * @param eventStartTime Start time of the event
     * @param eventEndTime End time of the event
     * @param eventEndDate End date of the event
     */
    public Event(int eventID, String eventTitle, Date eventStartDate, String location, String eventDescription, String eventStartTime, String eventEndTime, Date eventEndDate) {
        this.eventID = eventID;
        this.eventTitle = eventTitle;
        this.eventStartDate = eventStartDate;
        this.location = location;
        this.eventDescription = eventDescription;
        this.eventStartTime = eventStartTime;
        this.eventEndTime = eventEndTime;
        this.eventEndDate = eventEndDate;
    }

    /**
     * Gets the event's unique identifier.
     * @return The event ID
     */
    public int getEventID() {
        return eventID;
    }

    /**
     * Gets the event's title.
     * @return The event title
     */
    public String geteventTitle() {
        return eventTitle;
    }

    /**
     * Sets the event's title.
     * @param eventTitle The new event title
     */
    public void seteventTitle(String eventTitle) {
        this.eventTitle = eventTitle;
    }

    /**
     * Gets the event's start date.
     * @return The start date
     */
    public Date geteventStartDate() {
        return eventStartDate;
    }

    /**
     * Sets the event's start date.
     * @param eventStartDate The new start date
     */
    public void seteventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    /**
     * Gets the event's location.
     * @return The location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the event's location.
     * @param location The new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the event's start time.
     * @return The start time as a string
     */
    public String geteventStartTime() {
        return eventStartTime;
    }

    /**
     * Sets the event's start time.
     * @param eventStartTime The new start time
     */
    public void seteventStartTime(String eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    /**
     * Gets the event's end time.
     * @return The end time as a string
     */
    public String geteventEndTime() {
        return eventEndTime;
    }

    /**
     * Sets the event's end time.
     * @param eventEndTime The new end time
     */
    public void seteventEndTime(String eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    /**
     * Gets the event's description.
     * @return The event description
     */
    public String geteventDescription() {
        return eventDescription;
    }

    /**
     * Sets the event's description.
     * @param eventDescription The new event description
     */
    public void seteventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    /**
     * Gets the recommended transportation information.
     * @return The recommended transportation
     */
    public String getRecTransport() {
        return recTransport;
    }

    /**
     * Sets the recommended transportation information.
     * @param recTransport The new recommended transportation
     */
    public void setRecTransport(String recTransport) {
        this.recTransport = recTransport;
    }

    /**
     * Gets the event's start date (alternative method).
     * @return The start date
     */
    public Date getEventStartDate() {
        return eventStartDate;
    }

    /**
     * Sets the event's start date (alternative method).
     * @param eventStartDate The new start date
     */
    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    /**
     * Gets the event's end date.
     * @return The end date
     */
    public Date getEventEndDate() {
        return eventEndDate;
    }

    /**
     * Sets the event's end date.
     * @param eventEndDate The new end date
     */
    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    /**
     * Gets the generated event ID (used before permanent ID assignment).
     * @return The generated event ID
     */
    public int getGeneratedEventID() {
        return generatedEventID;
    }

    /**
     * Sets the generated event ID.
     * @param generatedEventID The new generated event ID
     */
    public void setGeneratedEventID(int generatedEventID) {
        this.generatedEventID = generatedEventID;
    }
}