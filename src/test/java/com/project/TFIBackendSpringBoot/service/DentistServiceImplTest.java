package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.model.Dentist;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
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

    @Test
    @Order(1)
    void save() {

        Dentist dentist=new Dentist();
        Set<Appointment> appointments=new HashSet<>();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setAppointments(appointments);
        dentist.setRole("user");

        dentistService.save(dentist);

        DentistDTO dentistBob=dentistService.search(1L);

        assertNotNull(dentistBob);

    }

    @Test
    @Order(2)
    void search() {
        Dentist dentist=new Dentist();
        Set<Appointment> appointments=new HashSet<>();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setAppointments(appointments);
        dentist.setRole("user");

        dentistService.save(dentist);

        Long id=1L;
        DentistDTO dentistBob=dentistService.search(1L);

        assertEquals(dentistBob.getId(),id);
    }

    @Test
    @Order(3)
    void searchAll() {
        Dentist dentist=new Dentist();
        Set<Appointment> appointments=new HashSet<>();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setAppointments(appointments);
        dentist.setRole("user");

        dentistService.save(dentist);

        Set<DentistDTO> allDentists=dentistService.searchAll();

        assertTrue(allDentists.size()>0);

    }

    @Test
    @Order(4)
    void modify() {
        Dentist dentist=new Dentist();
        Set<Appointment> appointments=new HashSet<>();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setAppointments(appointments);
        dentist.setRole("user");

        dentistService.save(dentist);

        DentistDTO notModifiedDentist=dentistService.search(1L);

        String name="Timothy";
        Long id=1L;
        Dentist dentist2=new Dentist();
        dentist2.setName(name);
        dentist2.setLastName("Tomasson");
        dentist2.setLicense("4-12356-6434");
        dentist2.setAppointments(appointments);
        dentist2.setRole("user");
        dentist2.setId(1L);

        dentistService.modify(dentist2);

        DentistDTO modifiedDentist= dentistService.search(1L);

        assertNotEquals(modifiedDentist.getName(),notModifiedDentist.getName());
        assertEquals(modifiedDentist.getLastName(),notModifiedDentist.getLastName());
        assertEquals(modifiedDentist.getId(),id);
    }

    @Test
    @Order(5)
    void remove() {
        Dentist dentist=new Dentist();
        Set<Appointment> appointments=new HashSet<>();
        dentist.setName("Bob");
        dentist.setLastName("Tomasson");
        dentist.setLicense("4-12356-6434");
        dentist.setAppointments(appointments);
        dentist.setRole("user");

        dentistService.save(dentist);

        Long id=1L;

        DentistDTO searchDentist=dentistService.search(id);

        dentistService.remove(id);
        assertNotNull(searchDentist);
        assertNull(dentistService.search(id));

    }
}