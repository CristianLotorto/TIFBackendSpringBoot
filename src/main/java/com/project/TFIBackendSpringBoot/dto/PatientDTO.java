package com.project.TFIBackendSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PatientDTO{
    private String name;
    private String lastName;
    private String DNI;
    private String address;
    private Set<AppointmentDTO> appointments;
    private Date dischargedDate;

}
