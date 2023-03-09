package com.mii.server.models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_leave")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @OneToMany(mappedBy = "leave")
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    // private List<Overtime> overtime;
    private Integer id;

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
