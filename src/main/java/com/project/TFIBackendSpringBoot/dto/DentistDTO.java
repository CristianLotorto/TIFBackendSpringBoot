package com.project.TFIBackendSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTO {
    private String name;
    private String lastName;
    private Set<AppointmentDTO> appointments;

}
