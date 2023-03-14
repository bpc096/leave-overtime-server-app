package com.mii.server.models.dto.requests;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmailRequest {
    private String to;
    private String subject;
    private String body;
    private String pathToAttachment;
    private String name;
    private String location;
    private String sign;
    private Map<String, Object> props;
}
