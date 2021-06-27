package com.lucasalvessm.meetingcontrol.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Optional;

import static com.lucasalvessm.meetingcontrol.shared.Constants.CLEAN_UP_BASE_TIME;
import static com.lucasalvessm.meetingcontrol.shared.Constants.CLEAN_UP_BASE_TIME_PER_SEAT;

@Entity
@Data
public class Room {

    @Id
    private Long id;
    private String name;
    private Long maxAllocation;
    private boolean multimedia;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FLOOR", referencedColumnName = "ID")
    private Floor floor;

    public Long getCleanUpTime() {
        return CLEAN_UP_BASE_TIME + (maxAllocation * CLEAN_UP_BASE_TIME_PER_SEAT);
    }

    public Long getBuildingId() {
        return this.getFloor().getBuilding().getId();
    }

    public Long getFloorId() {
        return this.getFloor().getId();
    }

    public Long getFloorNumber() {
        return this.getFloor().getFloorNumber();
    }

    public String getBuildingName() {
        return this.getFloor().getBuilding().getName();
    }

    public boolean isRoomUnqualifiedForMeeting(Long attendees, Boolean multimedia, Optional<Long> buildingId) {
        return attendees > this.getMaxAllocation()
                || multimedia && !this.isMultimedia()
                || !buildingId.isEmpty() && !buildingId.get().equals(this.getBuildingId());
    }

}
