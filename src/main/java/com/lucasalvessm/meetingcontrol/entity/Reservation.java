package com.lucasalvessm.meetingcontrol.entity;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@SuperBuilder
public class Reservation {

    public Reservation() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long buildingId;
    private Long floorId;
    private Long roomId;
    private Date startDatetime;
    private Date endDatetime;
    private Long meetingTimeRange;
    private Long cleanUpTime;
    private Date createdAt = new Date();
}
