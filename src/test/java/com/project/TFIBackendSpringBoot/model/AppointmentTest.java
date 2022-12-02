package com.project.TFIBackendSpringBoot.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AppointmentTest {
    @Test
    void testToString() {
        Set<Appointment> appointments = new HashSet<>();
        Dentist dentist = new Dentist(1L, "Richard", "Thompson", "13-18436-1564", appointments, "User");
        Patient patient = new Patient(1L, "Jose", "Gonzales", "35169745", "BabaYaga 1615", appointments, new Date(122, 4, 3), "User");
        Appointment appointment = new Appointment(1L, dentist, patient, new Date(122, 11, 13), new Time(19, 30, 00));

        String mostrar = appointment.toString();

        assertEquals("id: " + appointment.getId() +
                "\nDentist: " + appointment.getDentist() +
                "\nPatient: " + appointment.getPatient() +
                "\nDate: " + appointment.getAppointmentDate() +
                "\nTime: " + appointment.getAppointmentTime(), mostrar);

    }
}