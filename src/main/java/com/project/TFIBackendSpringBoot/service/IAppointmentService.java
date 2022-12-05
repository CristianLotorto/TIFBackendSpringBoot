package com.project.TFIBackendSpringBoot.service;

import java.sql.Date;
import java.util.Set;

public interface IAppointmentService <T,U> {
    public void save(U u);
    public void remove(Long id);
    public T search(Long id);
    public Set<T> searchAll();
    public void modify(U u);
}
