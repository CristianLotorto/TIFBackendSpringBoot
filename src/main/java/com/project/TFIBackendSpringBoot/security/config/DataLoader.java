package com.project.TFIBackendSpringBoot.security.config;

import com.project.TFIBackendSpringBoot.model.login.AppUser;
import com.project.TFIBackendSpringBoot.model.login.AppUserRole;
import com.project.TFIBackendSpringBoot.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository){
        this.userRepository=userRepository;
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder= new BCryptPasswordEncoder();
        String password=passwordEncoder.encode("password");
        String password2=passwordEncoder.encode("password2");

        userRepository.save(new AppUser("Cristian","cristian","cristianlotorto@gmail.com",password, AppUserRole.ADMIN));
        userRepository.save(new AppUser("Cristian2","cristian2","cristianlotorto2@gmail.com",password2, AppUserRole.USER));
    }
}
