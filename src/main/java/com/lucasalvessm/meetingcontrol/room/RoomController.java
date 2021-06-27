package com.lucasalvessm.meetingcontrol.room;

import com.lucasalvessm.meetingcontrol.room.dto.AvailableRoom;
import com.lucasalvessm.meetingcontrol.shared.RequestUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Operation(
            summary = "List all available rooms",
            description = "Request example: /?startDatetime=2021-06-24 22:30&meetingTimeRangeInMinutes=10&attendees=2&multimedia=false"
    )
    @GetMapping("/available")
    public ResponseEntity<List<AvailableRoom>> listAvailable(@RequestParam @Parameter(description = "Format: yyyy-MM-dd HH:mm") String startDatetime,
                                                             @RequestParam Long meetingTimeRangeInMinutes,
                                                             @RequestParam Long attendees,
                                                             @RequestParam Boolean multimedia,
                                                             @RequestParam(required = false) Optional<Long> buildingId) {


        Date date = RequestUtil.transformStringToDate(startDatetime);

        List<AvailableRoom> availableRoomList = roomService.listAvailable(date,
                meetingTimeRangeInMinutes,
                attendees,
                multimedia,
                buildingId);

        return ResponseEntity.ok(availableRoomList);
    }

}
