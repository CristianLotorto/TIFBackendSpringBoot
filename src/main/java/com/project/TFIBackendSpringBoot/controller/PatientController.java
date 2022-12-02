package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.PatientDTO;
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

    @GetMapping("/search/{id}")
    public ResponseEntity<PatientDTO> search(@PathVariable Long id){
        PatientDTO patientDTO= patientService.search(id);
        ResponseEntity response;
        if(patientDTO!=null){
            response=ResponseEntity.ok(patientDTO);
        }else{
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/search/all")
    public ResponseEntity<Set<PatientDTO>> searchAll(){
        ResponseEntity<Set<PatientDTO>> response;
        Set<PatientDTO> patientsDTOS=patientService.searchAll();

        if(patientsDTOS.isEmpty()){
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            response= ResponseEntity.ok(patientsDTOS);
        }

        return response;
    }

    @PostMapping("/save")
    public String save(@RequestBody Patient patient){

        patientService.save(patient);
        return "Patient saved SUCCESFULLY!";

    }

    @DeleteMapping
    public ResponseEntity<Patient> delete(@PathVariable Long id){
        ResponseEntity<Patient> response;
        if(patientService.search(id)==null){
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            patientService.remove(id);
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }
}
