package com.project.TFIBackendSpringBoot.service;

import java.util.Set;

public interface InterfaceService<T,U> {

    public void save(U u);
    public void remove(Long id);
    public T search(Long id);
    public Set<T> searchAll();
    public void modify(U u);

}
