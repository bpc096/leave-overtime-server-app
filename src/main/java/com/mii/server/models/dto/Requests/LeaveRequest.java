package com.mii.server.models.dto.requests;

import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class LeaveRequest {
    
    private Date startday;
    private Date endday;
    private LocalDateTime applydate;
    private String updateby;
    private String reason;
    private LocalDateTime respontime;
    private Integer employeeId;
}
