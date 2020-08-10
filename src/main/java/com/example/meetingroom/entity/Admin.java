package com.example.meetingroom.entity;

import com.example.meetingroom.engine.MeetingProcessorImpl;

public class Admin extends Member {

    private final MeetingProcessorImpl meetingProcessor;

    public Admin(MeetingProcessorImpl meetingProcessor) {
        super(null, null , null);
        this.meetingProcessor = meetingProcessor;
    }

    public void addRoom(Room room) {
        meetingProcessor.addRoom(room);
    }

    public void deleteRoom(Room room) {
        meetingProcessor.deleteRoom(room);
    }
}
