package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.PatientDTO;
import com.project.TFIBackendSpringBoot.dto.PatientDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.service.PatientServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@AllArgsConstructor
@RestController
@RequestMapping("/patient")
public class PatientController {

    private static Logger LOGGER= LogManager.getLogger();

    private final PatientServiceImpl patientService;


    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Everyone!";
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody PatientDTOSave patientDTOSave)throws ResourseAlreadyExistsExeption {

            patientService.save(patientDTOSave);
            LOGGER.info("Patient CREATED succesfully!");
            return ResponseEntity.ok("Patient SAVED successfully!");

    }

    @GetMapping("/search/{dni}")
    public ResponseEntity search(@PathVariable String dni)throws ResourseNotFoundException {

        PatientDTO patientDTO= patientService.search(dni);
        LOGGER.info("Patient FOUNDED succesfully!");
        return ResponseEntity.ok(patientDTO);

    }

    @GetMapping("/search/all")
    public ResponseEntity searchAll()throws ResourseNotFoundException{

        Set<PatientDTO> patientsDTO= patientService.searchAll();
        LOGGER.info("Patients FOUNDED succesfully!");
        return ResponseEntity.ok(patientsDTO);

    }

    @PutMapping("/modify")
    public ResponseEntity modify(@RequestBody PatientDTOSave patientDTOSave) throws ResourseNotFoundException {
        patientService.modify(patientDTOSave);

        LOGGER.info("Patient with DNI: "+patientDTOSave.getDNI()+" MODIFIED successfully!");
        return ResponseEntity.ok("Patient with DNI: "+patientDTOSave.getDNI()+" MODIFIED successfully!");
    }


    @DeleteMapping("/delete/{dni}")
    public ResponseEntity delete(@PathVariable String dni)throws ResourseNotFoundException{

            patientService.remove(dni);
            LOGGER.info("Patient DELETED succesfully!");
            return ResponseEntity.ok("Patient DELETED successfully: ");

    }
}
