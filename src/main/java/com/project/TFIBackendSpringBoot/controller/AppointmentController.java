package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.service.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private final AppointmentServiceImpl appointmentService;


    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Everyone!";
    }

    @PostMapping("/save")
    public String save(@RequestBody AppointmentDTOSave appointmentDTOSave){

        appointmentService.save(appointmentDTOSave);
        return "Appointment saved SUCCESFULLY!";

    }

    @GetMapping("/search")
    @ResponseBody
    public ResponseEntity<AppointmentDTO> search(@RequestParam Long id){
        AppointmentDTO appointmentDTO= appointmentService.search(id);
        ResponseEntity response;
        if(appointmentDTO!=null){
            response=ResponseEntity.ok(appointmentDTO);
        }else{
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/search/all")
    public ResponseEntity<Set<AppointmentDTO>> searchAll() throws ResourseNotFoundException {
        ResponseEntity<Set<AppointmentDTO>> response;
        Set<AppointmentDTO> appointmentsDTO=appointmentService.searchAll();

        if(appointmentsDTO.isEmpty()){
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            response= ResponseEntity.ok(appointmentsDTO);
        }

        return response;
    }

    @DeleteMapping("/delete/{date}")
    public ResponseEntity<Appointment> delete(@PathVariable Long id){
        ResponseEntity<Appointment> response;
        if(appointmentService.search(id)==null){
            response= new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            appointmentService.remove(id);
            response= new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }


}
