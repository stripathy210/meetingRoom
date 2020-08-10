package com.example.meetingroom.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Meeting {

    private Long meetingId;
    private Room room;
    private int noOfParticipants;
    private RoomTime time;

    public Meeting(Long meetingId, Room room, int noOfParticipants, RoomTime time) {
        this.meetingId = meetingId;
        this.room = room;
        this.noOfParticipants = noOfParticipants;
        this.time = time;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Meeting{");
        sb.append("meetingId=").append(meetingId);
        sb.append(", room=").append(room);
        sb.append(", noOfParticipants=").append(noOfParticipants);
        sb.append(", time=").append(time);
        sb.append('}');
        return sb.toString();
    }
}
