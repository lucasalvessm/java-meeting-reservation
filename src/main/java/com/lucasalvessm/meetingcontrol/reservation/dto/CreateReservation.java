package com.lucasalvessm.meetingcontrol.reservation.dto;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateReservation {
    @NotNull
    private Long roomId;
    @NotNull
    @Parameter(description = "Format: yyyy-MM-dd HH:mm")
    private String startDatetime;
    @NotNull
    private Long meetingTimeRangeInMinutes;
}
