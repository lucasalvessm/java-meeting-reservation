package com.lucasalvessm.meetingcontrol.repository;

import com.lucasalvessm.meetingcontrol.entity.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends CrudRepository<Room, Long> {
}
