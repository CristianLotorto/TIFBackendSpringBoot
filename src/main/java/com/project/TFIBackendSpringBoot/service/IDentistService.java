package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.exceptions.ResourseAlreadyExistsExeption;
import com.project.TFIBackendSpringBoot.exceptions.ResourseNotFoundException;

import java.util.Set;

public interface IDentistService<T, U> {

    public void save(U u) throws ResourseAlreadyExistsExeption;
    public void remove(String license) throws ResourseNotFoundException;
    public T search(String license) throws ResourseNotFoundException;
    public Set<T> searchAll() throws ResourseNotFoundException;
    public void modify(U u) throws ResourseNotFoundException;

}
