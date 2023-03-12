package com.mii.server.models;

import java.time.LocalDateTime;

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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_overtimehistory")
public class OvertimeHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private LocalDateTime applydate;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String updateby;

    @Column(nullable = true)
    private LocalDateTime respontime;

    // @ManyToOne
    // @JoinColumn(name = "employee_id", nullable = false)
    // private Employee employee;
    private String employee;

    // @ManyToOne
    // @JoinColumn(name = "status_id", nullable = false)
    // private Status status;
    private String status;

    @ManyToOne
    @JoinColumn(name = "overtime_id", nullable = false)
    private Overtime overtime;

}
