package com.project.TFIBackendSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.model.Patient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTOSave {
    private Long id;
    private Dentist dentist;
    private Patient patient;
    private Date appointmentDate;
    private Time appointmentTime;
}
