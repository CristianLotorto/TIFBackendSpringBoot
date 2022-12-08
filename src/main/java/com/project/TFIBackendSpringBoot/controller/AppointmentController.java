package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.service.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
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

    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable Long id) throws ResourseNotFoundException {

        AppointmentDTO appointmentDTO= appointmentService.search(id);

        return ResponseEntity.ok(appointmentDTO);

    }

    @GetMapping("/search/all")
    public ResponseEntity searchAll() throws ResourseNotFoundException {

        Set<AppointmentDTO> appointmentsDTO=appointmentService.searchAll();

        return ResponseEntity.ok(appointmentsDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws ResourseNotFoundException {

        appointmentService.remove(id);

        return "Appointment DELETED successfully";
    }

}
