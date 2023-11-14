package com.example.crudroom.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")

public class EventModal {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String eventName;
    public String description;
    public String type;

    public EventModal(String eventName, String description, String type) {
        this.eventName = eventName;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
