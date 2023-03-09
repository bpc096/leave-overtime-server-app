package com.mii.server.models;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_leave_history")
public class LeaveHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // @ManyToOne
    // @JoinColumn(name = "leave_id", nullable = false)
    // @Column(nullable = false)
    // private Integer leaveId;

    @Column(nullable = false)
    private Date startday;

    @Column(nullable = false)
    private Date endday;

    @Column(nullable = false)
    private LocalDateTime applydate;

    @Column(nullable = false)
    private String updateby;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = true)
    private LocalDateTime respontime;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private Status status;
}
