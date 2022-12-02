package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.model.Patient;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PatientServiceImplTest {

    private PatientServiceImpl patientService;

    @Autowired
    public PatientServiceImplTest(PatientServiceImpl patientService){
        this.patientService=patientService;
    }

    @Test
    @Order(1)
    void save() {

        Patient patient=new Patient();
        Set<Appointment> appointments=new HashSet<>();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setAppointments(appointments);
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);

        PatientDTO patientCharles=patientService.search(1L);

        assertNotNull(patientCharles);

    }

    @Test
    @Order(2)
    void search() {
        Patient patient=new Patient();
        Set<Appointment> appointments=new HashSet<>();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setAppointments(appointments);
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);

        Long id=1L;
        PatientDTO patientCharles=patientService.search(1L);

         assertEquals(patientCharles.getId(),id);
    }

    @Test
    @Order(3)
    void searchAll() {
        Patient patient=new Patient();
        Set<Appointment> appointments=new HashSet<>();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setAppointments(appointments);
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);

        Set<PatientDTO> allPatients=patientService.searchAll();

        assertTrue(allPatients.size()>0);

    }

    @Test
    @Order(4)
    void modify() {
        Patient patient=new Patient();
        Set<Appointment> appointments=new HashSet<>();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setAppointments(appointments);
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);

        PatientDTO notModifiedPatient=patientService.search(1L);

        String name="Simon";
        Long id=1L;
        Patient patient2=new Patient();
        patient2.setName(name);
        patient2.setLastName("Bronson");
        patient2.setDNI("41235664");
        patient2.setAddress("FifaStreet 1613");
        patient2.setAppointments(appointments);
        patient2.setDischargedDate(new Date(2022,9,7));
        patient2.setRole("user");
        patient2.setId(1L);


        patientService.modify(patient2);

        PatientDTO modifiedPatient= patientService.search(1L);

        assertNotEquals(modifiedPatient.getName(),notModifiedPatient.getName());
        assertEquals(modifiedPatient.getAddress(),notModifiedPatient.getAddress());
        assertEquals(modifiedPatient.getId(),id);
    }

    @Test
    @Order(5)
    void remove() {
        Patient patient=new Patient();
        Set<Appointment> appointments=new HashSet<>();
        patient.setName("Charles");
        patient.setLastName("Bronson");
        patient.setDNI("41235664");
        patient.setAddress("FifaStreet 1613");
        patient.setAppointments(appointments);
        patient.setDischargedDate(new Date(2022,9,7));
        patient.setRole("user");

        patientService.save(patient);

        Long id=1L;

        PatientDTO searchPatient=patientService.search(id);

            patientService.remove(id);
            assertNotNull(searchPatient);
            assertNull(patientService.search(id));

    }
}
