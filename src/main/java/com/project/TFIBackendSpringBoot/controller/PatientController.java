package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Patient;
import com.project.TFIBackendSpringBoot.service.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {


    private final PatientServiceImpl patientService;


    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Everyone!";
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PatientDTOSave patientDTOSave)throws ResourseAlreadyExistsExeption {

            patientService.save(patientDTOSave);

            return ResponseEntity.ok("Patient SAVED successfully!");

    }

    @GetMapping("/search/{dni}")
    public ResponseEntity search(@PathVariable String dni)throws ResourseNotFoundException {

        PatientDTO patientDTO= patientService.search(dni);

        return ResponseEntity.ok("Patient FOUNDED: ");

    }

    @GetMapping("/search/all")
    public ResponseEntity searchAll()throws ResourseNotFoundException{

        patientService.searchAll();

        return ResponseEntity.ok("Patients FOUNDED: ");

    }


    @DeleteMapping("/delete/{dni}")
    public ResponseEntity delete(@PathVariable String dni)throws ResourseNotFoundException{

            patientService.remove(dni);

            return ResponseEntity.ok("Patient DELETED successfully: ");

    }
}
