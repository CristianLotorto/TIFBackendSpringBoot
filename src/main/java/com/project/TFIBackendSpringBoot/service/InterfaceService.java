package com.project.TFIBackendSpringBoot.service;

import java.util.Set;

public interface InterfaceService<T> {

    public void save(T t);
    public void remove(Long id);
    public T search(Long id);
    public Set<T> searchAll();
    public void modify(T t);

}
