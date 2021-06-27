package com.lucasalvessm.meetingcontrol.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "BUILDING")
public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn( name = "ID_BUILDING", referencedColumnName = "ID")
    private List<Floor> floorList;

}
