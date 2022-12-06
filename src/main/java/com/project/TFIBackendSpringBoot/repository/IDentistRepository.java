package com.project.TFIBackendSpringBoot.repository;

import com.project.TFIBackendSpringBoot.model.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDentistRepository extends JpaRepository<Dentist,Long> {
    Dentist findByLicense(String license);

}
