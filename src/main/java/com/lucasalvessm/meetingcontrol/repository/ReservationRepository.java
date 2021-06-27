package com.lucasalvessm.meetingcontrol.repository;

import com.lucasalvessm.meetingcontrol.entity.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    @Query(value = "SELECT * FROM RESERVATION WHERE TRUNC(START_DATETIME) = TRUNC(:startDatetime)", nativeQuery = true)
    List<Reservation> findByStartDate(@Param("startDatetime") Date startDatetime);
}
