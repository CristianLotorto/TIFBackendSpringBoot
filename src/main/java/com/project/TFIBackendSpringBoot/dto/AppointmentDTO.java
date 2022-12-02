package com.project.TFIBackendSpringBoot.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppointmentDTO {
    private DentistDTO dentistDTO;
    private PatientDTO patientDTO;
    private Date appointmentDate;
    private Time appointmentTime;

}
