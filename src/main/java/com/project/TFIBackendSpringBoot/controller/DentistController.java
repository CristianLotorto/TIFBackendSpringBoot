package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.DentistDTO;
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

    @GetMapping("/search/{id}")
    public ResponseEntity<DentistDTO> search(@PathVariable Long id){
        DentistDTO dentistDTO= dentistService.search(id);
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
    public String save(@RequestBody DentistDTO dentistDTO){

        dentistService.save(dentistDTO);
        return "Dentist saved SUCCESFULLY!";

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Dentist> delete(@PathVariable Long id){
        ResponseEntity<Dentist> response;
        if(dentistService.search(id)==null){
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            dentistService.remove(id);
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

}
