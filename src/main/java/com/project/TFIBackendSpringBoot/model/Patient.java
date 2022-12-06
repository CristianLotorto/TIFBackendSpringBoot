package com.project.TFIBackendSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="patient")
public class Patient{

    @Id
    @SequenceGenerator(name= "patient_sequence", sequenceName="PATIENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="patient_sequence")
    private Long id;
    private String name;
    @Column(name="last_name")
    private String lastName;
    @Column(name="dni")
    private String DNI;
    private String address;
    @OneToMany(mappedBy="patient")
    @JsonIgnore
    private Set<Appointment> appointments= new HashSet<>();
    @Column(name="disch_date")
    private Date dischargedDate;
    private String role;

    @Override
    public String toString(){
        return  "Id: "+ this.getId() +
                "\nName: "+this.getName()+
                "\nLastName: "+this.getLastName()+
                "\nDNI: "+this.getDNI()+
                "\nAppointment: "+this.getAppointments()+
                "\nDischargeDate: "+this.getDischargedDate()+
                "\nRole: "+this.getRole();
    }
}