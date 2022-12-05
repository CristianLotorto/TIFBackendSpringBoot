package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.dto.DentistDTOSave;
import com.project.TFIBackendSpringBoot.model.Dentist;
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

    @GetMapping("/search/{license}")
    public ResponseEntity<DentistDTO> search(@PathVariable String license){
        DentistDTO dentistDTO= dentistService.search(license);
        ResponseEntity response;
        if(dentistDTO!=null){
            response=ResponseEntity.ok(dentistDTO);
        }else{
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/search/all")
    public ResponseEntity<Set<DentistDTO>> searchAll(){
        ResponseEntity<Set<DentistDTO>> response;
        Set<DentistDTO> dentistsDTO=dentistService.searchAll();

        if(dentistsDTO.isEmpty()){
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            response= ResponseEntity.ok(dentistsDTO);
        }

        return response;
    }

    @PostMapping("/save")
    public String save(@RequestBody DentistDTOSave dentistDTOSave){

        dentistService.save(dentistDTOSave);
        return "Dentist saved SUCCESFULLY!";

    }

    @DeleteMapping("/delete")
    public ResponseEntity<Dentist> delete(@RequestParam Long id, @RequestParam String license){
        ResponseEntity<Dentist> response;
        if(dentistService.search(license)==null){
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            dentistService.remove(id);
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

}
