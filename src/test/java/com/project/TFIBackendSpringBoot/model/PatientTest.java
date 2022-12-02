package com.project.TFIBackendSpringBoot.model;



import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.model.Patient;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientTest {

    @org.junit.jupiter.api.Test
    void testToString() {
        Set<Appointment> appointments= new HashSet<>();
        Patient patient=new Patient(1L,"Jose","Gonzales","35169745","BabaYaga 1615",appointments,new Date(122,4,3),"User");

        String mostrar=patient.toString();

        assertEquals( "Id: "+ patient.getId() +
                "\nName: "+patient.getName()+
                "\nLastName: "+patient.getLastName()+
                "\nDNI: "+patient.getDNI()+
                "\nAppointment: "+patient.getAppointments()+
                "\nDischargeDate: "+patient.getDischargedDate()+
                "\nRole: "+patient.getRole(),mostrar);

    }
}