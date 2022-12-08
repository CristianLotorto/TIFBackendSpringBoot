package com.project.TFIBackendSpringBoot.repository;

import com.project.TFIBackendSpringBoot.dto.DentistDTO;
import com.project.TFIBackendSpringBoot.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;


@Repository
public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
}
