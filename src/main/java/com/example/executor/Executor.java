package com.example.executor;

import com.example.meetingroom.engine.MeetingProcessorImpl;
import com.example.meetingroom.entity.Admin;
import com.example.meetingroom.entity.Meeting;
import com.example.meetingroom.entity.Member;
import com.example.meetingroom.entity.Participant;
import com.example.meetingroom.entity.Room;
import com.example.meetingroom.entity.RoomTime;

import java.util.List;

public class Executor {


    private Member admin;

    private Member participant;

    public Executor() {
    }

    public void execute () {
        MeetingProcessorImpl meetingProcessor = new MeetingProcessorImpl();
        Admin admin = new Admin(meetingProcessor);
        Participant participant = new Participant(meetingProcessor);

        Room room1 = Room.builder().roomId("1")
                .roomName("Room1")
                .isActive(true)
                .capacity(10)
                .build();

        Room room2 = Room.builder().roomId("2")
                .roomName("Room2")
                .isActive(true)
                .capacity(8)
                .build();

        admin.addRoom(room1);
        admin.addRoom(room2);

//        admin.deleteRoom(room1);

        List<Room> roomList = participant.getRoomForTime(new RoomTime("2", "3"), 8);

        Meeting meeting1 = participant.scheduleMtg(roomList.get(0).getRoomId(), new RoomTime("2", "3"), 10);
        Meeting meeting2 = participant.scheduleMtg(roomList.get(1).getRoomId(), new RoomTime("2", "3"), 8);

//        System.out.println(participant.reScheduleMtg(meeting2.getMeetingId(), new RoomTime("3","4")));

        participant.cancelMtg(meeting2.getMeetingId(), new RoomTime("2", "3"));

        Meeting meeting3 = participant.scheduleMtg(roomList.get(1).getRoomId(), new RoomTime("2", "3"), 8);
//
        System.out.println(meeting3);
//        System.out.println(meeting2);
//
//        roomList = participant.getRoomForTime(new RoomTime("2", "3"), 5);


        for (Room room : roomList) {
            System.out.println(room);
        }
    }


}
