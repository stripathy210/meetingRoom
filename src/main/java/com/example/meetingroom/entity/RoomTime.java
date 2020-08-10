package com.example.meetingroom.entity;

import lombok.Getter;

import java.util.Objects;

@Getter
public class RoomTime {
    private String startTime;
    private String endTime;
    private boolean isAvailable;

    public RoomTime(String startTime, String endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public boolean equals(Object obj) {
//    TODO Check for overlapping
        if (obj instanceof RoomTime) {
            RoomTime roomTime = (RoomTime) obj;

            if (roomTime.getStartTime() == this.startTime || roomTime.getEndTime() == this.endTime) {
                return true;
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RoomTime{");
        sb.append("startTime='").append(startTime).append('\'');
        sb.append(", endTime='").append(endTime).append('\'');
        sb.append(", isAvailable=").append(isAvailable);
        sb.append('}');
        return sb.toString();
    }
}
