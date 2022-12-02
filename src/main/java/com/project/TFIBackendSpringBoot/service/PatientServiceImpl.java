package com.project.TFIBackendSpringBoot.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.model.Patient;
import com.project.TFIBackendSpringBoot.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImpl implements InterfaceService<PatientDTO>{

    private IPatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(IPatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    @Autowired
    ObjectMapper mapper;

    public void savePatient(PatientDTO patientDTO){
        Patient patient=mapper.convertValue(patientDTO, Patient.class);

        patientRepository.save(patient);
    }

    @Override
    public void save(PatientDTO patientDTO) {
        savePatient(patientDTO);
    }

    @Override
    public void remove(Long id) {
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDTO search(Long id) {

        Patient patient= patientRepository.findById(id).orElse(null);

        PatientDTO patientDTO=mapper.convertValue(patient, PatientDTO.class);


        return patientDTO;
    }

    @Override
    public Set<PatientDTO> searchAll() {
        List<Patient> patients= patientRepository.findAll();
        Set<PatientDTO> patientsDTO=new HashSet<>();
        for (Patient patient:patients) {
            PatientDTO patientDTO=mapper.convertValue(patient,PatientDTO.class);
            patientsDTO.add(patientDTO);
        }
        return patientsDTO;
    }

    @Override
    public void modify(PatientDTO patientDTO) {
        savePatient(patientDTO);
    }
}
