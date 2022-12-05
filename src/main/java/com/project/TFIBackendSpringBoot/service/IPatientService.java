package com.project.TFIBackendSpringBoot.service;

import java.util.Set;

public interface IPatientService <T,U>{

    public void save(U u);
    public void remove(Long id);
    public T search(String dni);
    public Set<T> searchAll();
    public void modify(U u);
}
