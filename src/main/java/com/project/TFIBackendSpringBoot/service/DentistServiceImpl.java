package com.project.TFIBackendSpringBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.repository.IDentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DentistServiceImpl implements IDentistService<DentistDTO, DentistDTOSave> {

       private IDentistRepository dentistRepository;

       @Autowired
       public DentistServiceImpl(IDentistRepository dentistRepository){
              this.dentistRepository=dentistRepository;
       }

       @Autowired
       ObjectMapper mapper;

       public void saveDentist(DentistDTOSave dentistDTOSave){
              Dentist dentist=mapper.convertValue(dentistDTOSave, Dentist.class);

              dentistRepository.save(dentist);
       }

       @Override
       public void save(DentistDTOSave dentistDTOSave) {
              saveDentist(dentistDTOSave);
       }

       @Override
       public void remove(String license){
              try{
                     Dentist dentist=dentistRepository.findByLicense(license);
                     dentistRepository.deleteById(dentist.getId());

              }catch (RuntimeException e){
              System.out.println("Se re picó");
              System.out.println(e);
       }
       }

       @Override
       public DentistDTO search(String license) {


              Dentist dentist=dentistRepository.findByLicense(license);

              DentistDTO dentistDTO;

              dentistDTO=mapper.convertValue(dentist, DentistDTO.class);


              return dentistDTO;
       }

       @Override
       public Set<DentistDTO> searchAll() {
              List<Dentist> dentists=dentistRepository.findAll();
              Set<DentistDTO> dentistsDTO=new HashSet<>();
              for (Dentist dentist:dentists) {
                     DentistDTO dentistDTO=mapper.convertValue(dentist,DentistDTO.class);
                     dentistsDTO.add(dentistDTO);
              }
              return dentistsDTO;
       }

       @Override
       public void modify(DentistDTOSave dentistDTOSave) {
              saveDentist(dentistDTOSave);
       }
}
