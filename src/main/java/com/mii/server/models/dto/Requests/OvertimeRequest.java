package com.mii.server.models.dto.requests;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class OvertimeRequest {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private LocalDateTime applydate;
    private String purpose;
    private String updateby;
    private Integer responsetime;
    private Integer employeeId;
    private Integer statusId;
    private Integer projectId;

}
