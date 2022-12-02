package com.project.TFIBackendSpringBoot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.TFIBackendSpringBoot.model.Appointment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DentistDTOSave {
    private Long id;
    private String name;
    private String lastName;
    private Set<Appointment> appointments= new HashSet<>();
    private String role;
}
