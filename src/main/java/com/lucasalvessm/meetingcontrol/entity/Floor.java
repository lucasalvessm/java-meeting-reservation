package com.lucasalvessm.meetingcontrol.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Floor {

    @Id
    private Long id;
    private Long floorNumber;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_FLOOR", referencedColumnName = "ID")
    private List<Room> roomList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_BUILDING", referencedColumnName = "ID")
    private Building building;
}
