package com.project.TFIBackendSpringBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.repository.IDentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DentistServiceImpl implements InterfaceService<DentistDTO,Dentist> {

       private IDentistRepository dentistRepository;

       @Autowired
       public DentistServiceImpl(IDentistRepository dentistRepository){
              this.dentistRepository=dentistRepository;
       }

       @Autowired
       ObjectMapper mapper;

       public void saveDentist(Dentist dentist){
              dentistRepository.save(dentist);
       }

       @Override
       public void save(Dentist dentist) {
              saveDentist(dentist);
       }

       @Override
       public void remove(Long id) {
              dentistRepository.deleteById(id);
       }

       @Override
       public DentistDTO search(Long id) {

              Dentist dentist=dentistRepository.findById(id).orElse(null);

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
       public void modify(Dentist dentist) {
              saveDentist(dentist);
       }
}
