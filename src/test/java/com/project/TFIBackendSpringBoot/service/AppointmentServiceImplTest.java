package com.project.TFIBackendSpringBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.model.Patient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentServiceImplTest {

    private DentistServiceImpl dentistService;
    private PatientServiceImpl patientService;
    private AppointmentServiceImpl appointmentService;


    @Autowired
    public AppointmentServiceImplTest(AppointmentServiceImpl appointmentService, DentistServiceImpl dentistService, PatientServiceImpl patientService){
        this.appointmentService=appointmentService;
        this.dentistService=dentistService;
        this.patientService=patientService;
    }


    @Test
    @Order(1)
    void save() {
        Dentist dentist=new Dentist();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setRole("user");

        dentistService.save(dentist);

        Patient patient=new Patient();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);
        ObjectMapper mapper=new ObjectMapper();

        Dentist realDentist=mapper.convertValue(dentistService.search(1L),Dentist.class);
        Patient realPatient=mapper.convertValue(patientService.search(2L),Patient.class);

        Appointment appointment=new Appointment();
        appointment.setDentist(realDentist);
        appointment.setPatient(realPatient);
        appointment.setAppointmentDate(new Date(122,9,5));
        appointment.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointment);

        AppointmentDTO appointmentSearch=appointmentService.search(3L);

        assertNotNull(appointmentSearch);

    }

    @Test
    @Order(2)
    void search() {
        Dentist dentist=new Dentist();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setRole("user");

        dentistService.save(dentist);

        Patient patient=new Patient();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);
        ObjectMapper mapper=new ObjectMapper();

        Dentist realDentist=mapper.convertValue(dentistService.search(1L),Dentist.class);
        Patient realPatient=mapper.convertValue(patientService.search(2L),Patient.class);

        Appointment appointment=new Appointment();
        appointment.setDentist(realDentist);
        appointment.setPatient(realPatient);
        appointment.setAppointmentDate(new Date(122,9,5));
        appointment.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointment);

        Long id=3L;
        AppointmentDTO appointmentSearch=appointmentService.search(3L);

        assertEquals(appointmentSearch.getId(),id);
    }

    @Test
    @Order(3)
    void searchAll() {
        Dentist dentist=new Dentist();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setRole("user");

        dentistService.save(dentist);

        Patient patient=new Patient();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);
        ObjectMapper mapper=new ObjectMapper();

        Dentist realDentist=mapper.convertValue(dentistService.search(1L),Dentist.class);
        Patient realPatient=mapper.convertValue(patientService.search(2L),Patient.class);

        Appointment appointment=new Appointment();
        appointment.setDentist(realDentist);
        appointment.setPatient(realPatient);
        appointment.setAppointmentDate(new Date(122,9,5));
        appointment.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointment);

        Set<AppointmentDTO> allAppointments=appointmentService.searchAll();

        assertTrue(allAppointments.size()>0);

    }

    @Test
    @Order(4)
    void modify() {
        Dentist dentist=new Dentist();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setRole("user");

        dentistService.save(dentist);

        Patient patient=new Patient();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);
        ObjectMapper mapper=new ObjectMapper();

        Dentist realDentist=mapper.convertValue(dentistService.search(1L),Dentist.class);
        Patient realPatient=mapper.convertValue(patientService.search(2L),Patient.class);

        Appointment appointment=new Appointment();
        appointment.setDentist(realDentist);
        appointment.setPatient(realPatient);
        appointment.setAppointmentDate(new Date(122,9,5));
        appointment.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointment);

        AppointmentDTO notModifiedAppointment=appointmentService.search(3L);

        Date date=new Date(122,11,9);
        Long id=3L;
        Appointment appointment2=new Appointment();
        appointment2.setDentist(realDentist);
        appointment2.setPatient(realPatient);
        appointment2.setAppointmentDate(date);
        appointment2.setAppointmentTime(new Time(15,25,00));
        appointment2.setId(id);

        appointmentService.modify(appointment2);

        AppointmentDTO modifiedAppointment= appointmentService.search(3L);

        assertNotEquals(modifiedAppointment.getAppointmentDate(),notModifiedAppointment.getAppointmentDate());
        assertEquals(modifiedAppointment.getAppointmentTime(),notModifiedAppointment.getAppointmentTime());
        assertEquals(modifiedAppointment.getId(),id);
    }

    @Test
    @Order(5)
    void remove() {
        Dentist dentist=new Dentist();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setRole("user");

        dentistService.save(dentist);

        Patient patient=new Patient();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);
        ObjectMapper mapper=new ObjectMapper();

        Dentist realDentist=mapper.convertValue(dentistService.search(1L),Dentist.class);
        Patient realPatient=mapper.convertValue(patientService.search(2L),Patient.class);

        Appointment appointment=new Appointment();
        appointment.setDentist(realDentist);
        appointment.setPatient(realPatient);
        appointment.setAppointmentDate(new Date(122,9,5));
        appointment.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointment);

        Long id=3L;

        AppointmentDTO searchAppointment=appointmentService.search(id);

        appointmentService.remove(id);
        assertNotNull(searchAppointment);
        assertNull(appointmentService.search(id));

    }
}