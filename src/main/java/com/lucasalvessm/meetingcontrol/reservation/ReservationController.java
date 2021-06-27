package com.lucasalvessm.meetingcontrol.reservation;

import com.lucasalvessm.meetingcontrol.entity.Reservation;
import com.lucasalvessm.meetingcontrol.reservation.dto.CreateReservation;
import com.lucasalvessm.meetingcontrol.reservation.dto.RoomWithReservations;
import com.lucasalvessm.meetingcontrol.shared.RequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Operation(
            summary = "Create a room reservation for a meeting",
            description = "Request body example: {\n" +
                    "  \"roomId\": 0,\n" +
                    "  \"startDatetime\": \"2020-12-30 10:00\",\n" +
                    "  \"meetingTimeRangeInMinutes\": 0\n" +
                    "}"
    )
    @PostMapping
    public ResponseEntity<Reservation> createReservation(@RequestBody @Valid CreateReservation request) {

        Date date = RequestUtil.transformStringToDate(request.getStartDatetime());

        Reservation reservation = reservationService.createReservation(request, date);

        return ResponseEntity.ok(reservation);
    }


    @Operation(summary = "List all reservations by room")
    @GetMapping("/by-room")
    public ResponseEntity<List<RoomWithReservations>> getReservationListByRoom() {
        List<RoomWithReservations> list = reservationService.getReservationListByRoom();

        return ResponseEntity.ok(list);
    }

}
