package com.lucasalvessm.meetingcontrol.reservation.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReservationInfo {
    private Date startDatetime;
    private Date endDatetime;
    private Long meetingTimeRange;
    private Long cleanUpTime;
}
