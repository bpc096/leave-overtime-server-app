package com.mii.server.models.dto.requests;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class OvertimeRequest {
    private Integer count;
    private LocalDateTime applydate;
    private Integer status;

}
