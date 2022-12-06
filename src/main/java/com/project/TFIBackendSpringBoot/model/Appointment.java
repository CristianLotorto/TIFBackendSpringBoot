package com.project.TFIBackendSpringBoot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="appointment")
public class Appointment {

    @Id
    @SequenceGenerator(name= "appointment_sequence", sequenceName="APPOINTMENT_SEQ")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="appointment_sequence")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dentist_id", nullable = false)
    private Dentist dentist;
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    @Column(name = "date")
    private Date appointmentDate;
    @Column(name = "time")
    private Time appointmentTime;

    @Override
    public String toString(){
        return "id: "+this.getId()+
                "\nDentist: "+this.getDentist()+
                "\nPatient: "+this.getPatient()+
                "\nDate: "+this.getAppointmentDate()+
                "\nTime: "+this.getAppointmentTime();
    }
}
