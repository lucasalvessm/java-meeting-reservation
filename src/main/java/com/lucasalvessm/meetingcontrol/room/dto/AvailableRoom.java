package com.lucasalvessm.meetingcontrol.room.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AvailableRoom {
    private Long id;
    private String name;
    private Long maxAllocation;
    private boolean multimedia;
    private String buildingName;
    private Long floor;
}
