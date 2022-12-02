package com.project.TFIBackendSpringBoot.model;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DentistTest {

    @Test
    void testToString() {
        Set<Appointment> appointments= new HashSet<>();
        Dentist dentist=new Dentist(1L,"Richard","Thompson","13-18436-1564",appointments,"User");

        String mostrar=dentist.toString();

        assertEquals("Id: "+dentist.getId()+
                "\nName: "+dentist.getName()+
                "\nLastName: "+dentist.getLastName()+
                "\nLicense: "+dentist.getLicense()+
                "\nAppointments: "+dentist.getAppointments()+
                "\nRole: "+dentist.getRole(),mostrar);
    }
}