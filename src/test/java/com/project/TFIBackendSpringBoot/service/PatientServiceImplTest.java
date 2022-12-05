package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
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

        PatientDTOSave patientDTOSave=new PatientDTOSave();
        patientDTOSave.setName("Charles");
        patientDTOSave.setLastName("Bronson");
        patientDTOSave.setDNI("41235664");
        patientDTOSave.setAddress("FifaStreet 1613");
        patientDTOSave.setDischargedDate(new Date(2022,9,7));
        patientDTOSave.setRole("user");

        patientService.save(patientDTOSave);

        PatientDTO patientCharles=patientService.search("41235664");

        assertNotNull(patientCharles);

    }

    @Test
    @Order(2)
    void search() {
        PatientDTOSave patientDTOSave=new PatientDTOSave();
        patientDTOSave.setName("Charles");
        patientDTOSave.setLastName("Bronson");
        patientDTOSave.setDNI("41235514");
        patientDTOSave.setAddress("FifaStreet 1613");
        patientDTOSave.setDischargedDate(new Date(2022,9,7));
        patientDTOSave.setRole("user");

        patientService.save(patientDTOSave);

        String patientName="Charles";
        PatientDTO patientCharles=patientService.search("41235514");

         assertEquals(patientCharles.getName(),patientName);
    }

    @Test
    @Order(3)
    void searchAll() {
        PatientDTOSave patientDTOSave=new PatientDTOSave();
        patientDTOSave.setName("Charles");
        patientDTOSave.setLastName("Bronson");
        patientDTOSave.setDNI("41698664");
        patientDTOSave.setAddress("FifaStreet 1613");
        patientDTOSave.setDischargedDate(new Date(2022,9,7));
        patientDTOSave.setRole("user");

        patientService.save(patientDTOSave);

        Set<PatientDTO> allPatients=patientService.searchAll();

        assertTrue(allPatients.size()>0);

    }

    @Test
    @Order(4)
    void modify() {
        PatientDTOSave patientDTOSave=new PatientDTOSave();
        patientDTOSave.setName("Charles");
        patientDTOSave.setLastName("Bronson");
        patientDTOSave.setDNI("32655148");
        patientDTOSave.setAddress("FifaStreet 1613");
        patientDTOSave.setDischargedDate(new Date(2022,9,7));
        patientDTOSave.setRole("user");

        patientService.save(patientDTOSave);

        PatientDTO notModifiedPatient=patientService.search("32655148");

        String name="Simon";
        PatientDTOSave patientDTOSave2=new PatientDTOSave();
        patientDTOSave2.setName(name);
        patientDTOSave2.setLastName("Bronson");
        patientDTOSave2.setDNI("32655148");
        patientDTOSave2.setAddress("FifaStreet 1613");
        patientDTOSave2.setDischargedDate(new Date(2022,9,7));
        patientDTOSave2.setRole("user");
        patientDTOSave2.setId(notModifiedPatient.getId());


        patientService.modify(patientDTOSave2);

        PatientDTO modifiedPatient= patientService.search("32655148");

        assertNotEquals(modifiedPatient.getName(),notModifiedPatient.getName());
        assertEquals(modifiedPatient.getAddress(),notModifiedPatient.getAddress());
        assertEquals(modifiedPatient.getName(),name);
    }

    @Test
    @Order(5)
    void remove() {
        PatientDTOSave patientDTOSave=new PatientDTOSave();
        patientDTOSave.setName("Charles");
        patientDTOSave.setLastName("Bronson");
        patientDTOSave.setDNI("46235664");
        patientDTOSave.setAddress("FifaStreet 1613");
        patientDTOSave.setDischargedDate(new Date(2022,9,7));
        patientDTOSave.setRole("user");

        patientService.save(patientDTOSave);


        PatientDTO searchPatient=patientService.search("46235664");

            patientService.remove(searchPatient.getId());
            assertNotNull(searchPatient);
            assertNull(patientService.search("46235664"));

    }
}
