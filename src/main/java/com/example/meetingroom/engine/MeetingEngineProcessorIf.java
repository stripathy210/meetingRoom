package com.example.meetingroom.engine;

import com.example.meetingroom.entity.Meeting;
import com.example.meetingroom.entity.Room;
import com.example.meetingroom.entity.RoomTime;

import java.util.List;

public interface MeetingEngineProcessorIf {

    Meeting scheduleMtg(String roomId, RoomTime roomTime, int noOfParticipants);
    void addRoom(Room room);
    void deleteRoom(Room room);
    boolean rescheduleMtg(Long meetingId, RoomTime newTime) ;
    boolean cancelMtg(Long meetingId, RoomTime roomTime);

    List<Room> getRoomForTime(RoomTime roomTime, int capacity) ;
}
