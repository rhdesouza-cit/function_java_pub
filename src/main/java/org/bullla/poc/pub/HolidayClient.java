package org.bullla.poc.pub;

import feign.RequestLine;

import java.util.List;

public interface HolidayClient {

    @RequestLine("GET /v1/json-server/gets")
    List<Holiday> getHoliday();

}