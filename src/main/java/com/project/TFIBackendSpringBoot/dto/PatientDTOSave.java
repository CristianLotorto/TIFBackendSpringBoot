package com.project.TFIBackendSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.TFIBackendSpringBoot.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTOSave {
    private Long id;
    private String name;
    private String lastName;
    private String DNI;
    private String address;
    private Set<Appointment> appointments= new HashSet<>();
    private Date dischargedDate;
    private String role;
}
