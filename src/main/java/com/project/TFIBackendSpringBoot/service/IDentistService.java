package com.project.TFIBackendSpringBoot.service;

import java.util.Set;

public interface IDentistService<T, U> {

    public void save(U u);
    public void remove(Long id);
    public T search(String license);
    public Set<T> searchAll();
    public void modify(U u);

}
