package com.project.TFIBackendSpringBoot.service;

import com.project.TFIBackendSpringBoot.model.login.AppUser;
import com.project.TFIBackendSpringBoot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public AppUserService(IUserRepository userRepository){
        this.userRepository=userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=userRepository.findByUsername(username).orElse(null);

        if(appUser!=null){
            return appUser;
        }else {
         throw new UsernameNotFoundException("Username NOT FOUND");
        }
    }
}
