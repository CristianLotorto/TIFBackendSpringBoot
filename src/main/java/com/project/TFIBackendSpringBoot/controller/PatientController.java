package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
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

    @GetMapping("/search/{dni}")
    public ResponseEntity<PatientDTO> search(@PathVariable String dni){
        PatientDTO patientDTO= patientService.search(dni);
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
    public String save(@RequestBody PatientDTOSave patientDTOSave){

        patientService.save(patientDTOSave);
        return "Patient saved SUCCESFULLY!";

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Patient> delete(@RequestParam Long id, @RequestParam String dni){
        ResponseEntity<Patient> response;
        if(patientService.search(dni)==null){
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            patientService.remove(id);
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }
}
