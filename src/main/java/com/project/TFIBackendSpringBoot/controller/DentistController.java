package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Dentist;
import com.project.TFIBackendSpringBoot.service.DentistServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private static Logger LOGGER= LogManager.getLogger();

    private final DentistServiceImpl dentistService;

    public DentistController(DentistServiceImpl dentistService){this.dentistService=dentistService;}

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Everyone!";
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody DentistDTOSave dentistDTOSave)throws ResourseAlreadyExistsExeption {

            dentistService.save(dentistDTOSave);
            LOGGER.info("Dentist CREATED succesfully!");
            return ResponseEntity.ok("Dentist CREATED succesfully!");

    }

    @GetMapping("/search/{license}")
    public ResponseEntity search(@PathVariable String license)throws ResourseNotFoundException{

            DentistDTO dentistDTO= dentistService.search(license);
            LOGGER.info("Dentist FOUNDED succesfully!");
            return ResponseEntity.ok(dentistDTO);
    }

    @GetMapping("/search/all")
    public ResponseEntity searchAll()throws ResourseNotFoundException{

            Set<DentistDTO> dentistsDTO= dentistService.searchAll();
            LOGGER.info("Dentists FOUNDED succesfully!");
            return ResponseEntity.ok(dentistsDTO);

    }

    @PutMapping("/modify")
    public ResponseEntity modify(@RequestBody DentistDTOSave dentistDTOSave) throws ResourseNotFoundException {
        dentistService.modify(dentistDTOSave);
        LOGGER.info("Dentist with License: "+dentistDTOSave.getLicense()+" MODIFIED successfully!");
        return ResponseEntity.ok("Dentist with License: "+dentistDTOSave.getLicense()+" MODIFIED successfully!");
    }


    @DeleteMapping("/delete/{license}")
    public ResponseEntity delete(@PathVariable String license)throws ResourseNotFoundException {

            dentistService.remove(license);
            LOGGER.info("Dentist REMOVED succesfully!");
            return ResponseEntity.ok("Dentist DELETED succesfully!");
    }


}
