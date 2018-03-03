package com.trivadis.blockchain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Represents an event related to a lift (i.e. : a user takes a lift)
 */
public class LiftEvent  implements Serializable{

    private Long userId;
    private Long liftId;
    private Date eventDate;


    public LiftEvent() {
    }

    public LiftEvent(String jsonData) {


    }

    public LiftEvent(Long userId, Long liftId, Date eventDate) {
        this.userId = userId;
        this.liftId = liftId;
        this.eventDate = eventDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getLiftId() {
        return liftId;
    }

    public void setLiftId(Long liftId) {
        this.liftId = liftId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
}
