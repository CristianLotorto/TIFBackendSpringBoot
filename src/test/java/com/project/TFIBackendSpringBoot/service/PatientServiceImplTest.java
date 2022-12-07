package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
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

    public void instanceEntity() throws ResourseAlreadyExistsExeption {

        PatientDTOSave patientDTOSave=new PatientDTOSave();
        patientDTOSave.setName("Charles");
        patientDTOSave.setLastName("Bronson");
        patientDTOSave.setDNI("41235664");
        patientDTOSave.setAddress("FifaStreet 1613");
        patientDTOSave.setDischargedDate(new Date(2022,9,7));
        patientDTOSave.setRole("user");

        patientService.save(patientDTOSave);

    }

    @Test
    @Order(1)
    void save() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {

        if(patientService.searchAll().isEmpty()){
            instanceEntity();
        }

        PatientDTO patientCharles=patientService.search("41235664");

        assertNotNull(patientCharles);

    }

    @Test
    @Order(2)
    void search() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(patientService.searchAll().isEmpty()){
            instanceEntity();
        }

        String patientName="Charles";
        PatientDTO patientCharles=patientService.search("41235664");

         assertEquals(patientCharles.getName(),patientName);
    }

    @Test
    @Order(3)
    void searchAll() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(patientService.searchAll().isEmpty()){
            instanceEntity();
        }

        Set<PatientDTO> allPatients=patientService.searchAll();

        assertTrue(allPatients.size()>0);

    }

    @Test
    @Order(4)
    void modify() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(patientService.searchAll().isEmpty()){
            instanceEntity();
        }

        PatientDTO notModifiedPatient=patientService.search("41235664");

        String name="Simon";
        PatientDTOSave patientDTOSave2=new PatientDTOSave();
        patientDTOSave2.setName(name);
        patientDTOSave2.setLastName("Bronson");
        patientDTOSave2.setDNI("41235664");
        patientDTOSave2.setAddress("FifaStreet 1613");
        patientDTOSave2.setDischargedDate(new Date(2022,9,7));
        patientDTOSave2.setRole("user");
        patientDTOSave2.setId(notModifiedPatient.getId());


        patientService.modify(patientDTOSave2);

        PatientDTO modifiedPatient= patientService.search("41235664");

        assertNotEquals(modifiedPatient.getName(),notModifiedPatient.getName());
        assertEquals(modifiedPatient.getAddress(),notModifiedPatient.getAddress());
        assertEquals(modifiedPatient.getName(),name);
    }

    @Test
    @Order(5)
    void remove() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(patientService.searchAll().isEmpty()){
            instanceEntity();
        }


        PatientDTO searchPatient=patientService.search("41235664");

            patientService.remove("41235664");
            assertNotNull(searchPatient);
            assertNull(patientService.search("41235664"));

    }
}
