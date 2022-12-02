package com.project.TFIBackendSpringBoot.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTOSave;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.repository.IAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AppointmentServiceImpl implements InterfaceService<AppointmentDTO, AppointmentDTOSave>{

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
    public void remove(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public AppointmentDTO search(Long id) {

        Optional<Appointment> optionalAppointment= appointmentRepository.findById(id);

        Appointment appointment=optionalAppointment.orElse(null);

        AppointmentDTO appointmentDTO=mapper.convertValue(appointment,AppointmentDTO.class);

        appointmentDTO.setDentistDTO(dentistService.search(appointment.getDentist().getId()));

        appointmentDTO.setPatientDTO(patientService.search(appointment.getPatient().getId()));

        return appointmentDTO;
    }

    @Override
    public Set<AppointmentDTO> searchAll() {
        List<Appointment> appointments= appointmentRepository.findAll();
        Set<AppointmentDTO> appointmentsDTO=new HashSet<>();

        for (Appointment appointment:appointments) {
            AppointmentDTO appointmentDTO=mapper.convertValue(appointment,AppointmentDTO.class);

            appointmentDTO.setDentistDTO(dentistService.search(appointment.getDentist().getId()));

            appointmentDTO.setPatientDTO(patientService.search(appointment.getPatient().getId()));

            appointmentsDTO.add(appointmentDTO);
        }

        return appointmentsDTO;
    }

    @Override
    public void modify(AppointmentDTOSave appointmentDTOSave) {
        saveAppointment(appointmentDTOSave);
    }
}
