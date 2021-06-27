package com.lucasalvessm.meetingcontrol.shared;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RequestUtil {

    public static Date transformStringToDate(String dateAsString) {

        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            Date date = format.parse(dateAsString);
            Date currentDate = new Date();

            if (currentDate.after(date)) {
                throw new BadRequestException("The startDatetime must be grater than current datetime");
            }
            return date;

        } catch (ParseException e) {
            throw new BadRequestException("The pattern expected for field startDatetime is: yyyy-MM-dd HH:mm");
        }
    }
}
