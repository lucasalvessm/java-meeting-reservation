package com.lucasalvessm.meetingcontrol.room;

import com.lucasalvessm.meetingcontrol.entity.Room;

import java.util.Comparator;

public class EfficiencyRoomComparator implements Comparator<Room> {
    @Override
    public int compare(Room room1, Room room2) {
        final long room1MaxAllocation = room1.getMaxAllocation();
        final long room2MaxAllocation = room2.getMaxAllocation();

        if (room1MaxAllocation < room2MaxAllocation) return -1;
        if (room2MaxAllocation < room1MaxAllocation) return 1;

        if (room2MaxAllocation == room1MaxAllocation) {
            if (!room1.isMultimedia() && room2.isMultimedia()) return -1;
            if (room1.isMultimedia() && !room2.isMultimedia()) return 1;
        }

        return 0;
    }
}
