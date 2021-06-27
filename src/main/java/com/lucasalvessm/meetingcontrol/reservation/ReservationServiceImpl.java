package com.lucasalvessm.meetingcontrol.reservation;

import com.lucasalvessm.meetingcontrol.entity.Reservation;
import com.lucasalvessm.meetingcontrol.entity.Room;
import com.lucasalvessm.meetingcontrol.repository.ReservationRepository;
import com.lucasalvessm.meetingcontrol.reservation.dto.CreateReservation;
import com.lucasalvessm.meetingcontrol.reservation.dto.ReservationInfo;
import com.lucasalvessm.meetingcontrol.reservation.dto.RoomWithReservations;
import com.lucasalvessm.meetingcontrol.room.RoomService;
import com.lucasalvessm.meetingcontrol.shared.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomService roomService;

    @Override
    public List<Reservation> findAllByDate(Date startDatetime) {
        return reservationRepository.findByStartDate(startDatetime);
    }

    @Override
    public Reservation createReservation(CreateReservation request, Date startDatetime) {

        Room room = roomService.getById(request.getRoomId());

        if (!this.isRoomAvailableForReservationAtTimeSpan(startDatetime, request.getMeetingTimeRangeInMinutes(), room)) {
            throw new BusinessException("Room is unavailable for informed time span");
        }

        LocalDateTime localEndDatetime = LocalDateTime
                .ofInstant(startDatetime.toInstant(), ZoneId.systemDefault())
                .plusMinutes(request.getMeetingTimeRangeInMinutes())
                .plusMinutes(room.getCleanUpTime());

        Date endDatetime = Date.from(localEndDatetime.atZone(ZoneId.systemDefault()).toInstant());

        Reservation reservation = Reservation
                .builder()
                .buildingId(room.getBuildingId())
                .cleanUpTime(room.getCleanUpTime())
                .roomId(room.getId())
                .floorId(room.getFloorId())
                .startDatetime(startDatetime)
                .endDatetime(endDatetime)
                .meetingTimeRange(request.getMeetingTimeRangeInMinutes())
                .createdAt(new Date())
                .build();

        return reservationRepository.save(reservation);
    }

    @Override
    public boolean isRoomAvailableForReservationAtTimeSpan(Date startDatetime,
                                                           Long timeSpan,
                                                           Room room) {

        List<Reservation> reservationList = reservationRepository.findByStartDate(startDatetime);

        return this.isRoomAvailableForReservationAtTimeSpan(startDatetime,
                timeSpan,
                room,
                reservationList);
    }

    @Override
    public boolean isRoomAvailableForReservationAtTimeSpan(Date startDatetime, Long timeSpan, Room room, List<Reservation> reservationList) {

        List<Reservation> filteredReservationList = reservationList
                .stream()
                .filter(reservation -> reservation.getRoomId().equals(room.getId()))
                .collect(Collectors.toList());

        if (filteredReservationList.isEmpty()) return true;

        LocalDateTime startDateTimeToCheckAvailability = LocalDateTime.ofInstant(startDatetime.toInstant(), ZoneId.systemDefault());
        LocalDateTime endDateTimeToCheckAvailability = startDateTimeToCheckAvailability.plusMinutes(timeSpan).plusMinutes(room.getCleanUpTime());

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

    @Override
    public List<RoomWithReservations> getReservationListByRoom() {
        List<Reservation> reservationList = (List<Reservation>) reservationRepository.findAll();

        List<Room> roomList = roomService.findAll();

        List<Long> distinctReservedRoomIdList =
                reservationList
                        .stream()
                        .map(reservation -> reservation.getRoomId()).distinct().collect(Collectors.toList());

        List<RoomWithReservations> roomWithReservationsList = distinctReservedRoomIdList.stream().map(roomId -> {

            Room room = roomList.stream().filter(r -> r.getId().equals(roomId)).findFirst().get();

            List<ReservationInfo> reservationInfoList = reservationList.stream()
                    .filter(reservation -> reservation.getRoomId().equals(roomId))
                    .map(reservation ->
                            ReservationInfo
                                    .builder()
                                    .cleanUpTime(reservation.getCleanUpTime())
                                    .meetingTimeRange(reservation.getMeetingTimeRange())
                                    .endDatetime(reservation.getEndDatetime())
                                    .startDatetime(reservation.getStartDatetime())
                                    .build()
                    ).collect(Collectors.toList());

            return RoomWithReservations
                    .builder()
                    .id(room.getId())
                    .maxAllocation(room.getMaxAllocation())
                    .multimedia(room.isMultimedia())
                    .name(room.getName())
                    .reservationList(reservationInfoList)
                    .floor(room.getFloorNumber())
                    .buildingName(room.getBuildingName())
                    .build();
        }).collect(Collectors.toList());


        return roomWithReservationsList;
    }
}
