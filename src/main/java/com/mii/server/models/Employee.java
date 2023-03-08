package com.mii.server.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_employee")
public class Employee {
    @Id
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private Integer idmanager;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private Date birthdate;

    @Column(nullable = true)
    private String address;

    @Column(nullable = true, unique = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    @OneToOne
    @MapsId
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JoinColumn(name = "id")
    private User user;
}
