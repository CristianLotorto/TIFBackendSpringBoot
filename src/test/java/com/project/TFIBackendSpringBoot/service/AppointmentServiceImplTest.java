package com.project.TFIBackendSpringBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTOSave;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.model.Patient;
import com.project.TFIBackendSpringBoot.repository.IAppointmentRepository;
import com.project.TFIBackendSpringBoot.repository.IDentistRepository;
import com.project.TFIBackendSpringBoot.repository.IPatientRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AppointmentServiceImplTest {

    private DentistServiceImpl dentistService;
    private IDentistRepository dentistRepository;
    private PatientServiceImpl patientService;
    private IPatientRepository patientRepository;
    private AppointmentServiceImpl appointmentService;

    private IAppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentServiceImplTest(AppointmentServiceImpl appointmentService, DentistServiceImpl dentistService, PatientServiceImpl patientService, IPatientRepository patientRepository, IDentistRepository dentistRepository, IAppointmentRepository appointmentRepository){
        this.appointmentService=appointmentService;
        this.dentistService=dentistService;
        this.patientService=patientService;
        this.dentistRepository=dentistRepository;
        this.patientRepository=patientRepository;
        this.appointmentRepository=appointmentRepository;
    }
    public void instanceEntity() throws ResourseAlreadyExistsExeption {
    DentistDTOSave dentistDTOSave=new DentistDTOSave();
        dentistDTOSave.setName("Bob");
        dentistDTOSave.setLastName("Tomasson");
        dentistDTOSave.setLicense("4-12356-6434");
        dentistDTOSave.setRole("user");

        dentistService.save(dentistDTOSave);
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
        if(dentistRepository.findAll().isEmpty()||patientRepository.findAll().isEmpty()) {
            instanceEntity();
        }

        ObjectMapper mapper=new ObjectMapper();

        Dentist dentistEntity=mapper.convertValue(dentistService.search("4-12356-6434"),Dentist.class);
        Patient patientEntity=mapper.convertValue(patientService.search("41235664"),Patient.class);

        AppointmentDTOSave appointmentDTOSave=new AppointmentDTOSave();
        appointmentDTOSave.setDentist(dentistEntity);
        appointmentDTOSave.setPatient(patientEntity);
        appointmentDTOSave.setAppointmentDate(new Date(122,9,5));
        appointmentDTOSave.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointmentDTOSave);

        AppointmentDTO appointmentSearch=appointmentService.search(1L);

        assertNotNull(appointmentSearch);

    }

    @Test
    @Order(2)
    void search() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistRepository.findAll().isEmpty()||patientRepository.findAll().isEmpty()) {
            instanceEntity();
        }

        ObjectMapper mapper=new ObjectMapper();

        Dentist dentistEntity=mapper.convertValue(dentistService.search("4-12356-6434"),Dentist.class);
        Patient patientEntity=mapper.convertValue(patientService.search("41235664"),Patient.class);

        AppointmentDTOSave appointmentDTOSave=new AppointmentDTOSave();
        appointmentDTOSave.setDentist(dentistEntity);
        appointmentDTOSave.setPatient(patientEntity);
        appointmentDTOSave.setAppointmentDate(new Date(122,9,5));
        appointmentDTOSave.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointmentDTOSave);

        String patientName="Charles";
        AppointmentDTO appointmentSearch=appointmentService.search(1L);

        assertEquals(appointmentSearch.getPatientDTO().getName(),patientName);
    }

    @Test
    @Order(3)
    void searchAll() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistRepository.findAll().isEmpty()||patientRepository.findAll().isEmpty()) {
            instanceEntity();
        }
        ObjectMapper mapper=new ObjectMapper();

        Dentist dentistEntity=mapper.convertValue(dentistService.search("4-12356-6434"),Dentist.class);
        Patient patientEntity=mapper.convertValue(patientService.search("41235664"),Patient.class);

        AppointmentDTOSave appointmentDTOSave=new AppointmentDTOSave();
        appointmentDTOSave.setDentist(dentistEntity);
        appointmentDTOSave.setPatient(patientEntity);
        appointmentDTOSave.setAppointmentDate(new Date(122,9,5));
        appointmentDTOSave.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointmentDTOSave);

        Set<AppointmentDTO> allAppointments=appointmentService.searchAll();

        assertTrue(allAppointments.size()>0);

    }

    @Test
    @Order(4)
    void modify() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistRepository.findAll().isEmpty()||patientRepository.findAll().isEmpty()) {
            instanceEntity();
        }
        ObjectMapper mapper=new ObjectMapper();

        Dentist dentistEntity=mapper.convertValue(dentistService.search("4-12356-6434"),Dentist.class);
        Patient patientEntity=mapper.convertValue(patientService.search("41235664"),Patient.class);

        AppointmentDTOSave appointmentDTOSave=new AppointmentDTOSave();
        appointmentDTOSave.setDentist(dentistEntity);
        appointmentDTOSave.setPatient(patientEntity);
        appointmentDTOSave.setAppointmentDate(new Date(122,9,5));
        appointmentDTOSave.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointmentDTOSave);

        AppointmentDTO notModifiedAppointment=appointmentService.search(1L);

        Date date=new Date(122,11,9);
        Long id=1L;
        String dentistName="Bob";
        AppointmentDTOSave appointmentDTOSave2=new AppointmentDTOSave();
        appointmentDTOSave2.setDentist(dentistEntity);
        appointmentDTOSave2.setPatient(patientEntity);
        appointmentDTOSave2.setAppointmentDate(date);
        appointmentDTOSave2.setAppointmentTime(new Time(15,25,00));
        appointmentDTOSave2.setId(id);

        appointmentService.modify(appointmentDTOSave2);

        AppointmentDTO modifiedAppointment= appointmentService.search(1L);

        assertNotEquals(modifiedAppointment.getAppointmentDate(),notModifiedAppointment.getAppointmentDate());
        assertEquals(modifiedAppointment.getAppointmentTime(),notModifiedAppointment.getAppointmentTime());
        assertEquals(modifiedAppointment.getDentistDTO().getName(),dentistName);
    }

    @Test
    @Order(5)
    void remove() throws ResourseNotFoundException, ResourseAlreadyExistsExeption {
        if(dentistRepository.findAll().isEmpty()||patientRepository.findAll().isEmpty()) {
            instanceEntity();
        }
        ObjectMapper mapper=new ObjectMapper();

        Dentist dentistEntity=mapper.convertValue(dentistService.search("4-12356-6434"),Dentist.class);
        Patient patientEntity=mapper.convertValue(patientService.search("41235664"),Patient.class);

        AppointmentDTOSave appointmentDTOSave=new AppointmentDTOSave();
        appointmentDTOSave.setDentist(dentistEntity);
        appointmentDTOSave.setPatient(patientEntity);
        appointmentDTOSave.setAppointmentDate(new Date(122,9,5));
        appointmentDTOSave.setAppointmentTime(new Time(15,25,00));

        appointmentService.save(appointmentDTOSave);

        Long id=1L;

        AppointmentDTO searchAppointment=appointmentService.search(id);

        appointmentService.remove(id);
        assertNotNull(searchAppointment);
        assertNull(appointmentRepository.findById(id).orElse(null));

    }
}