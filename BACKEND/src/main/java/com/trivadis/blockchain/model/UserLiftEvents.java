package com.trivadis.blockchain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserLiftEvents implements Serializable {


    private String userId;

    private List<LiftEvent> eventList;

    public UserLiftEvents() {
    }

    public UserLiftEvents(String userId) {
        this.userId = userId;
        this.eventList = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<LiftEvent> getEventList() {
        return eventList;
    }

    public void setEventList(List<LiftEvent> eventList) {
        this.eventList = eventList;
    }

    public void addEvent(LiftEvent event) {
        eventList.add(event);
    }




}