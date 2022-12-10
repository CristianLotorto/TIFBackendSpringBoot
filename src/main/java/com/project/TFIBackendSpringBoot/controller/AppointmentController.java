package com.project.TFIBackendSpringBoot.controller;


import com.project.TFIBackendSpringBoot.dto.AppointmentDTO;
import com.project.TFIBackendSpringBoot.dto.AppointmentDTOSave;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;
import com.project.TFIBackendSpringBoot.model.Appointment;
import com.project.TFIBackendSpringBoot.service.AppointmentServiceImpl;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.persistence.PostUpdate;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    private static Logger LOGGER= LogManager.getLogger();

    private final AppointmentServiceImpl appointmentService;


    @GetMapping("/welcome")
    public String welcome(){
        return "Hello Everyone!";
    }

    @PostMapping("/save")
    public String save(@RequestBody AppointmentDTOSave appointmentDTOSave){

        appointmentService.save(appointmentDTOSave);
        LOGGER.info("Appointment CREATED succesfully!");
        return "Appointment saved SUCCESFULLY!";

    }

    @GetMapping("/search/{id}")
    public ResponseEntity search(@PathVariable Long id) throws ResourseNotFoundException {

        AppointmentDTO appointmentDTO= appointmentService.search(id);
        LOGGER.info("Appointment FOUNDED succesfully!");
        return ResponseEntity.ok(appointmentDTO);

    }

    @GetMapping("/search/all")
    public ResponseEntity searchAll() throws ResourseNotFoundException {

        Set<AppointmentDTO> appointmentsDTO=appointmentService.searchAll();
        LOGGER.info("Appointments FOUNDED succesfully!");
        return ResponseEntity.ok(appointmentsDTO);
    }

    @PutMapping("/modify")
    public ResponseEntity modify(@RequestBody AppointmentDTOSave appointmentDTOSave) throws ResourseNotFoundException {

        appointmentService.modify(appointmentDTOSave);
        LOGGER.info("Appointment MODIFIED succesfully!");
        return ResponseEntity.ok("Appointmet with id: "+appointmentDTOSave.getId()+" MODIFIED successfully");
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws ResourseNotFoundException {

        appointmentService.remove(id);
        LOGGER.info("Appointment DELETED succesfully!");
        return "Appointment DELETED successfully";
    }

}
