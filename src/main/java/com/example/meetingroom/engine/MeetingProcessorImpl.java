package com.example.meetingroom.engine;

import com.example.meetingroom.entity.Meeting;
import com.example.meetingroom.entity.Room;
import com.example.meetingroom.entity.RoomTime;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class MeetingProcessorImpl implements MeetingEngineProcessorIf {

    private ConcurrentHashMap<String, Room> roomDetails;
    private ConcurrentHashMap<Long, Meeting> meetingDetails;
    private ReentrantLock lock = new ReentrantLock();
    private Long meetingId = 1l;

    public MeetingProcessorImpl() {
        roomDetails = new ConcurrentHashMap<>();
        meetingDetails = new ConcurrentHashMap<>();
    }


    @Override
    public Meeting scheduleMtg(String roomId, RoomTime roomTime, int noOfParticipants) {
        try {
//            TODO optimized locking
            lock.lock();
            if (roomDetails.containsKey(roomId)) {
                Room room = roomDetails.get(roomId);

                if (room.getBlockedTimings().contains(roomTime)) {
                    return null;
                }
                room.getBlockedTimings().add(roomTime);

                Meeting meeting = new Meeting(meetingId, room, noOfParticipants, roomTime);
                meetingDetails.put(meetingId, meeting);
                meetingId++;

                return meeting;
            }
        }
        finally {
            lock.unlock();
        }

        return null;
    }

    @Override
    public void addRoom(Room room) {
        room.setBlockedTimings(new HashSet<>());
        roomDetails.put(room.getRoomId(), room);
    }

    @Override
    public void deleteRoom(Room room) {
        roomDetails.remove(room.getRoomId());
    }

    @Override
    public boolean rescheduleMtg(Long meetingId, RoomTime newTime) {
        try {
            lock.lock();
            if (meetingDetails.containsKey(meetingId)) {
                Meeting meeting = meetingDetails.get(meetingId);
//                remove old time
                meeting.getRoom().getBlockedTimings().remove(meeting.getTime());
                meeting.getRoom().getBlockedTimings().add(newTime);

                meeting.setTime(newTime);
                return true;
            }
        }
        finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public boolean cancelMtg(Long meetingId, RoomTime time) {
        try {
            lock.lock();
            if (meetingDetails.containsKey(meetingId)) {
                Meeting meeting = meetingDetails.get(meetingId);

                meeting.getRoom().getBlockedTimings().remove(time);
                meetingDetails.remove(meetingId);
                return true;
            }
        }
        finally {
            lock.unlock();
        }
        return false;
    }

    @Override
    public List<Room> getRoomForTime(RoomTime roomTime, int capacity) {

        List<Room> list = new ArrayList<>();
        for (Map.Entry<String,Room> entry: roomDetails.entrySet()) {
            Room room = entry.getValue();

            if (!room.getBlockedTimings().contains(roomTime) && room.getCapacity() >= capacity) {
                list.add(room);
            }
        }

        return list;

    }

}
