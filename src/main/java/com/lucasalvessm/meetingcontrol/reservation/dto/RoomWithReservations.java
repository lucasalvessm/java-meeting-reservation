package com.lucasalvessm.meetingcontrol.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoomWithReservations {
    private Long id;
    private String name;
    private Long maxAllocation;
    private boolean multimedia;
    private Long floor;
    private String buildingName;
    private List<ReservationInfo> reservationList;
}
