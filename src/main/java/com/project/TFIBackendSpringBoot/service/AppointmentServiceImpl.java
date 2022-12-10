package com.project.TFIBackendSpringBoot.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.repository.IAppointmentRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentServiceImpl implements IAppointmentService<AppointmentDTO, AppointmentDTOSave> {

    private static Logger LOGGER= LogManager.getLogger();

    private IAppointmentRepository appointmentRepository;
    private DentistServiceImpl dentistService;
    private PatientServiceImpl patientService;

    @Autowired
    public AppointmentServiceImpl(IAppointmentRepository appointmentRepository, DentistServiceImpl dentistService, PatientServiceImpl patientService){
        this.appointmentRepository=appointmentRepository;
        this.dentistService=dentistService;
        this.patientService=patientService;

    }

    @Autowired
    private ObjectMapper mapper;


    public void saveAppointment(AppointmentDTOSave appointmentDTOSave){
        Appointment appointment=mapper.convertValue(appointmentDTOSave, Appointment.class);

        appointmentRepository.save(appointment);

    }

    @Override
    public void save(AppointmentDTOSave appointmentDTOSave) {

        saveAppointment(appointmentDTOSave);

    }


    @Override
    public void remove(Long id)throws ResourseNotFoundException {

        Appointment appointment=appointmentRepository.findById(id).orElse(null);

        if (appointment!=null){

            appointmentRepository.deleteById(id);

        }else{
            LOGGER.error("Exception in Appointment SEARCHALL method. Appointment with id: "+id+" doesn't exists in database.");
            throw new ResourseNotFoundException("Appointment with id: "+id+" doesn't exists in database");
        }

    }

    @Override
    public AppointmentDTO search(Long id) throws ResourseNotFoundException {

            Appointment appointment =appointmentRepository.findById(id).orElse(null);


            if (appointment != null) {
                AppointmentDTO appointmentDTO=mapper.convertValue(appointment,AppointmentDTO.class);
                appointmentDTO.setDentistDTO(dentistService.search(appointment.getDentist().getLicense()));
                appointmentDTO.setPatientDTO(patientService.search(appointment.getPatient().getDNI()));

                return appointmentDTO;
            }else{

                LOGGER.error("Exception in Appointment SEARCHALL method. Appointment with id: "+id+" doesn't exists in database.");
                throw new ResourseNotFoundException("Appointment with id: "+id+" doesn't exists in database");
            }

    }

    @Override
    public Set<AppointmentDTO> searchAll() throws ResourseNotFoundException {
        List<Appointment> appointments= appointmentRepository.findAll();
        Set<AppointmentDTO> appointmentsDTO=new HashSet<>();

        if (appointments.isEmpty()) {
            LOGGER.info("Exception in Appointment SEARCHALL method. Appointment List is EMPTY");
            throw new ResourseNotFoundException("Appointments list is empty");
        }else{

        for (Appointment appointment:appointments) {

            AppointmentDTO appointmentDTO=mapper.convertValue(appointment,AppointmentDTO.class);

            appointmentDTO.setDentistDTO(dentistService.search(appointment.getDentist().getLicense()));

            appointmentDTO.setPatientDTO(patientService.search(appointment.getPatient().getDNI()));

            appointmentsDTO.add(appointmentDTO);
        }

        return appointmentsDTO;

        }

    }

    @Override
    public void modify(AppointmentDTOSave appointmentDTOSave) {
        saveAppointment(appointmentDTOSave);
    }
}
