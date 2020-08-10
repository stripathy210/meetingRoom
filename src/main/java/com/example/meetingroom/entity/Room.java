package com.example.meetingroom.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Builder
public class Room {
    private String roomId;
    private String roomName;
    private boolean isActive;
    private int capacity;

    private Set<RoomTime> blockedTimings;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Room{");
        sb.append("roomId='").append(roomId).append('\'');
        sb.append(", roomName='").append(roomName).append('\'');
        sb.append(", isActive=").append(isActive);
        sb.append(", capacity=").append(capacity);
        sb.append('}');
        return sb.toString();
    }
}
