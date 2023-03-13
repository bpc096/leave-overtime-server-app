package com.mii.server.models.dto.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OvertimeRequest {
    private Integer count;
    private LocalDateTime applydate;
    private String purpose;
    private String updateby;
    private Integer responsetime;
    private Integer employeeId;
    private Integer statusId;
    private Integer projectId;

}
