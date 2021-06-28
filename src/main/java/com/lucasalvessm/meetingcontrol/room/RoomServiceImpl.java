package com.lucasalvessm.meetingcontrol.room;

import com.lucasalvessm.meetingcontrol.entity.Reservation;
import com.lucasalvessm.meetingcontrol.entity.Room;
import com.lucasalvessm.meetingcontrol.repository.RoomRepository;
import com.lucasalvessm.meetingcontrol.reservation.ReservationService;
import com.lucasalvessm.meetingcontrol.room.dto.AvailableRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationService reservationService;

    @Override
    public List<AvailableRoom> listAvailable(Date startDatetime,
                                             Long meetingTimeRangeInMinutes,
                                             Long attendees,
                                             Boolean multimedia,
                                             Optional<Long> buildingId) {

        List<Room> roomList = (List<Room>) roomRepository.findAll();


        List<Reservation> reservationList = reservationService.findAllByDate(startDatetime);

        List<Room> availableRoomList = roomList.stream().filter(room -> {

            if (room.isRoomUnqualifiedForMeeting(attendees, multimedia, buildingId)) return false;

            if (reservationService.isRoomAvailableForReservationAtTimeSpan(startDatetime, meetingTimeRangeInMinutes, room, reservationList))
                return true;

            return false;
        }).collect(Collectors.toList());

        availableRoomList.sort(new EfficiencyRoomComparator());


        return availableRoomList
                .stream()
                .map(room -> AvailableRoom
                        .builder()
                        .id(room.getId())
                        .name(room.getName())
                        .buildingName(room.getFloor().getBuilding().getName())
                        .floor(room.getFloor().getFloorNumber())
                        .maxAllocation(room.getMaxAllocation())
                        .multimedia(room.isMultimedia())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public Room getById(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new MissingResourceException("Could not find a Room for provided id", "Room", "" + roomId));
    }

    @Override
    public List<Room> findAll() {
        return (List<Room>) roomRepository.findAll();
    }

    public boolean isRoomAvailableForReservationAtTimeSpan(List<Reservation> reservationList,
                                                           Room room,
                                                           LocalDateTime startDateTimeToCheckAvailability,
                                                           LocalDateTime endDateTimeToCheckAvailability) {

        List<Reservation> filteredReservationList = reservationList
                .stream()
                .filter(reservation -> reservation.getRoomId().equals(room.getId()))
                .collect(Collectors.toList());

        if (filteredReservationList.isEmpty()) return true;

        return !filteredReservationList
                .stream()
                .anyMatch(reservation -> {
                    LocalDateTime reservationStartDateTime = LocalDateTime.ofInstant(reservation.getStartDatetime().toInstant(), ZoneId.systemDefault());
                    LocalDateTime reservationEndDateTime = LocalDateTime.ofInstant(reservation.getEndDatetime().toInstant(), ZoneId.systemDefault());

                    if (startDateTimeToCheckAvailability.isAfter(reservationEndDateTime)
                            || endDateTimeToCheckAvailability.isBefore(reservationStartDateTime)) {
                        return false;
                    }

                    return true;
                });
    }
}
