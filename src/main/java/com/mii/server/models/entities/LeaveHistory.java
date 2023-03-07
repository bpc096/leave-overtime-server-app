package com.mii.server.models.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_leave")
public class LeaveHistory {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String createdby;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private String status;
}
