package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;

import java.sql.Date;
import java.util.Set;

public interface IAppointmentService <T,U> {
    public void save(U u);
    public void remove(Long id) throws ResourseNotFoundException;
    public T search(Long id) throws ResourseNotFoundException;
    public Set<T> searchAll() throws ResourseNotFoundException;
    public void modify(U u) throws ResourseNotFoundException;
}
