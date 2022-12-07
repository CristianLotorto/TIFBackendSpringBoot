package com.project.TFIBackendSpringBoot.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Patient;
import com.project.TFIBackendSpringBoot.repository.IPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PatientServiceImpl implements IPatientService<PatientDTO, PatientDTOSave> {

    private IPatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(IPatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }

    @Autowired
    ObjectMapper mapper;

    public void savePatient(PatientDTOSave patientDTOSave){
        Patient patient=mapper.convertValue(patientDTOSave, Patient.class);

        patientRepository.save(patient);
    }

    @Override
    public void save(PatientDTOSave patientDTOSave)throws ResourseAlreadyExistsExeption {
        Patient patient=patientRepository.findByDNI(patientDTOSave.getDNI());

        if (patient==null){

            savePatient(patientDTOSave);

        }else{

            throw new ResourseAlreadyExistsExeption("Patient with DNI: " + patientDTOSave.getDNI() + " is already loaded in database");

        }

    }

    @Override
    public void remove(String dni)throws ResourseNotFoundException {

            Patient patient=patientRepository.findByDNI(dni);

            if (patient==null){
                throw new ResourseNotFoundException("Patient with DNI: " + dni + " doesn't exist in database");
            }else{

                patientRepository.deleteById(patient.getId());

            }



    }

    @Override
    public PatientDTO search(String dni)throws ResourseNotFoundException {

        Patient patient= patientRepository.findByDNI(dni);

        if (patient==null){
            throw new ResourseNotFoundException("Patient with DNI: " + dni + " doesn't exist in database");
        }else{

            PatientDTO patientDTO=mapper.convertValue(patient, PatientDTO.class);

            return patientDTO;

        }



    }

    @Override
    public Set<PatientDTO> searchAll()throws ResourseNotFoundException {

        List<Patient> patients= patientRepository.findAll();

        if (patients.isEmpty()){
            throw new ResourseNotFoundException("Patients list is empty");
        }else{

        Set<PatientDTO> patientsDTO=new HashSet<>();
        for (Patient patient:patients) {
            PatientDTO patientDTO=mapper.convertValue(patient,PatientDTO.class);
            patientsDTO.add(patientDTO);
        }

            return patientsDTO;

        }
    }

    @Override
    public void modify(PatientDTOSave patientDTOSave) {
        savePatient(patientDTOSave);
    }
}
