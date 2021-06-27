package com.lucasalvessm.meetingcontrol.room;

import com.lucasalvessm.meetingcontrol.entity.Room;
import com.lucasalvessm.meetingcontrol.room.dto.AvailableRoom;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RoomService {

    List<AvailableRoom> listAvailable(Date startDatetime,
                                      Long meetingTimeRangeInMinutes,
                                      Long attendees,
                                      Boolean multimedia,
                                      Optional<Long> buildingId);

    Room getById(Long roomId);

    List<Room> findAll();
}
