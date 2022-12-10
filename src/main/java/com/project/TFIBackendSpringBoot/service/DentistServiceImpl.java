package com.project.TFIBackendSpringBoot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.repository.IDentistRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DentistServiceImpl implements IDentistService<DentistDTO, DentistDTOSave> {

       private static Logger LOGGER= LogManager.getLogger();

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
       public void save(DentistDTOSave dentistDTOSave)throws ResourseAlreadyExistsExeption {

              Dentist dentist= dentistRepository.findByLicense(dentistDTOSave.getLicense());

              if (dentist==null){

                     saveDentist(dentistDTOSave);

              }else{
                     LOGGER.info("Exception in Dentist SAVE method. Dentist with license: " + dentistDTOSave.getLicense() + " is already loaded in database");
                     throw new ResourseAlreadyExistsExeption("Dentist with license: " + dentistDTOSave.getLicense() + " is already loaded in database");
              }
       }

       @Override
       public void remove(String license)throws ResourseNotFoundException {
              if(dentistRepository.findByLicense(license)==null) {
                     LOGGER.error("Exception in Dentist REMOVE method. Dentist with license: " + license + " doesn't exist in database");
                     throw new ResourseNotFoundException("Dentist with license: " + license + " doesn't exist in database");
              }else{

                     Dentist dentist=dentistRepository.findByLicense(license);

                     dentistRepository.deleteById(dentist.getId());
              }
       }

       @Override
       public DentistDTO search(String license)throws ResourseNotFoundException {

              Dentist dentist=dentistRepository.findByLicense(license);

              if (dentist==null) {
                     LOGGER.error("Exception in Dentist SEARCH method. Dentist with license: " + license + " doesn't exist in database");
                     throw new ResourseNotFoundException("Dentist with license: " + license + " doesn't exist in database");
              }else{

                     return mapper.convertValue(dentist, DentistDTO.class);

              }
       }

       @Override
       public Set<DentistDTO> searchAll()throws ResourseNotFoundException {

              List<Dentist> dentists=dentistRepository.findAll();

              if (dentists.isEmpty()){
                     LOGGER.info("Exception in Dentist SEARCHALL method. Dentist List is EMPTY");
                     throw new ResourseNotFoundException("Dentists list is empty");
              }else{

                     Set<DentistDTO> dentistsDTO=new HashSet<>();
                     for (Dentist dentist:dentists) {
                            DentistDTO dentistDTO=mapper.convertValue(dentist,DentistDTO.class);
                            dentistsDTO.add(dentistDTO);
                     }
                     return dentistsDTO;

              }

       }

       @Override
       public void modify(DentistDTOSave dentistDTOSave)throws ResourseNotFoundException {
              Dentist dentist=dentistRepository.findByLicense(dentistDTOSave.getLicense());
              if(dentist==null){
                     LOGGER.error("Exception in Dentist MODIFY method. Dentist with License: "+dentistDTOSave.getLicense()+" doesn't exists in database.");
                     throw new ResourseNotFoundException("Dentist with license: " + dentistDTOSave.getLicense() + " doesn't exist in database");
              }else{

                     saveDentist(dentistDTOSave);

              }
       }
}
