package com.project.TFIBackendSpringBoot.repository;

import com.project.TFIBackendSpringBoot.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

    Patient findByDNI(String dni);

}
