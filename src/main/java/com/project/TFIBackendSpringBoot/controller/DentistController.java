package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.service.DentistServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/dentist")
public class DentistController {

    private final DentistServiceImpl dentistService;

    public DentistController(DentistServiceImpl dentistService){this.dentistService=dentistService;}

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Everyone!";
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody DentistDTOSave dentistDTOSave)throws ResourseAlreadyExistsExeption {

            dentistService.save(dentistDTOSave);

            return ResponseEntity.ok("Dentist CREATED succesfully!");

    }

    @GetMapping("/search/{license}")
    public ResponseEntity search(@PathVariable String license)throws ResourseNotFoundException{

            dentistService.search(license);

            return ResponseEntity.ok("Dentist FOUNDED: ");
    }

    @GetMapping("/search/all")
    public ResponseEntity searchAll()throws ResourseNotFoundException{

            dentistService.searchAll();

            return ResponseEntity.ok("Dentist FOUNDED: ");

    }


    @DeleteMapping("/delete/{license}")
    public ResponseEntity delete(@PathVariable String license)throws ResourseNotFoundException {

            dentistService.remove(license);

            return ResponseEntity.ok("Dentist DELETED succesfully!");
    }


}
