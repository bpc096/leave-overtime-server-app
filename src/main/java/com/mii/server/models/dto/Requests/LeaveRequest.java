package com.mii.server.models.dto.requests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class LeaveRequest {

    private LocalDate startday;
    private LocalDate endday;
    private LocalDateTime applydate;
    private String updateby;
    private String reason;
    private LocalDateTime respontime;
    private Integer employeeId;
    private Integer statusId;
}
