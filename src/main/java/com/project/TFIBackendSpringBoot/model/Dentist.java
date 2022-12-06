package com.project.TFIBackendSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="dentist")
public class Dentist {

    @Id
    @SequenceGenerator(name= "dentist_sequence", sequenceName="DENTIST_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="dentist_sequence")
    private Long id;
    private String name;
    @Column(name="last_name")
    private String lastName;
    private String license;
    @OneToMany(mappedBy="dentist")
    @JsonIgnore
    private Set<Appointment> appointments= new HashSet<>();
    private String role;

    @Override
    public String toString(){
        return  "Id: "+this.getId()+
                "\nName: "+this.getName()+
                "\nLastName: "+this.getLastName()+
                "\nLicense: "+this.getLicense()+
                "\nAppointments: "+this.getAppointments()+
                "\nRole: "+this.getRole();
    }

}
