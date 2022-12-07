package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DentistServiceImplTest {

    private DentistServiceImpl dentistService;

    @Autowired
    public DentistServiceImplTest(DentistServiceImpl dentistService){
        this.dentistService=dentistService;
    }

    public void instanceEntity() throws ResourseAlreadyExistsExeption {
        DentistDTOSave dentistDTOSave=new DentistDTOSave();
        dentistDTOSave.setName("Bob");
        dentistDTOSave.setLastName("Tomasson");
        dentistDTOSave.setLicense("4-12356-6434");
        dentistDTOSave.setRole("user");

        dentistService.save(dentistDTOSave);
    }

    @Test
    @Order(1)
    void save() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {

        if(dentistService.searchAll().isEmpty()){
            instanceEntity();
        }

        DentistDTO dentistBob=dentistService.search("4-12356-6434");

        assertNotNull(dentistBob);

    }

    @Test
    @Order(2)
    void search() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistService.searchAll().isEmpty()){
            instanceEntity();
        }

        String dentistName="Bob";
        DentistDTO dentistBob=dentistService.search("4-12356-6434");

        assertEquals(dentistBob.getName(),dentistName);
    }

    @Test
    @Order(3)
    void searchAll() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistService.searchAll().isEmpty()){
            instanceEntity();
        }

        Set<DentistDTO> allDentists=dentistService.searchAll();

        assertTrue(allDentists.size()>0);

    }

    @Test
    @Order(4)
    void modify() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistService.searchAll().isEmpty()){
            instanceEntity();
        }

        DentistDTO notModifiedDentist=dentistService.search("4-12356-6434");

        String name="Timothy";
        DentistDTOSave dentistDTOSave2=new DentistDTOSave();
        dentistDTOSave2.setName(name);
        dentistDTOSave2.setLastName("Tomasson");
        dentistDTOSave2.setLicense("4-12356-6434");
        dentistDTOSave2.setRole("user");
        dentistDTOSave2.setId(notModifiedDentist.getId());

        dentistService.modify(dentistDTOSave2);

        DentistDTO modifiedDentist= dentistService.search("4-12356-6434");

        assertNotEquals(modifiedDentist.getName(),notModifiedDentist.getName());
        assertEquals(modifiedDentist.getLastName(),notModifiedDentist.getLastName());
        assertEquals(modifiedDentist.getName(),name);
    }

    @Test
    @Order(5)
    void remove() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistService.searchAll().isEmpty()){
            instanceEntity();
        }


        DentistDTO searchDentist=dentistService.search("4-12356-6434");

        dentistService.remove("4-12356-6434");
        assertNotNull(searchDentist);
        assertNull(dentistService.search("4-12356-6434"));

    }
}