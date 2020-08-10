package com.example.meetingroom.entity;

import com.example.meetingroom.engine.MeetingProcessorImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Participant extends Member {

    private final MeetingProcessorImpl meetingProcessor;
    @Autowired
    public Participant (MeetingProcessorImpl meetingProcessor) {
        super(null, null, null);
        this.meetingProcessor = meetingProcessor;
    }

    public Meeting scheduleMtg(String roomId, RoomTime roomTime, int capacity) {
        return meetingProcessor.scheduleMtg(roomId, roomTime, capacity);
    }

    public boolean reScheduleMtg(Long meetingId, RoomTime newTime) {
        return meetingProcessor.rescheduleMtg(meetingId, newTime);
    }

    public boolean cancelMtg(Long meetingId, RoomTime timee) {
        return meetingProcessor.cancelMtg(meetingId, timee);
    }

    public List<Room> getRoomForTime(RoomTime roomTime, int capacity) {
        return meetingProcessor.getRoomForTime(roomTime, capacity);
    }
}
