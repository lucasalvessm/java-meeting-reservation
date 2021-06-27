package com.lucasalvessm.meetingcontrol.reservation;

import com.lucasalvessm.meetingcontrol.entity.Reservation;
import com.lucasalvessm.meetingcontrol.entity.Room;
import com.lucasalvessm.meetingcontrol.reservation.dto.CreateReservation;
import com.lucasalvessm.meetingcontrol.reservation.dto.RoomWithReservations;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<Reservation> findAllByDate(Date startDatetime);

    Reservation createReservation(CreateReservation request, Date startDatetime);

    boolean isRoomAvailableForReservationAtTimeSpan(Date startDatetime,
                                                    Long timeSpan,
                                                    Room room);

    boolean isRoomAvailableForReservationAtTimeSpan(Date startDatetime,
                                                    Long timeSpan,
                                                    Room room,
                                                    List<Reservation> reservationList);

    List<RoomWithReservations> getReservationListByRoom();
}
