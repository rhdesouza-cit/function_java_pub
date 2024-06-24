package org.bullla;

import feign.RequestLine;

import java.util.List;

public interface HolidayClient {

    @RequestLine("GET /messages-holiday")
    List<Holiday> getHoliday();

}