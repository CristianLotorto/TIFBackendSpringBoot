package com.project.TFIBackendSpringBoot.security.config;

import com.project.TFIBackendSpringBoot.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AppUserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/patient/**").hasRole("ADMIN")
                .antMatchers("/dentist/**").hasRole("ADMIN")
                .antMatchers("/appointment/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/patient/search/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/dentist/search/**").hasAnyRole("USER","ADMIN")
                .antMatchers("/appointment/search/**").hasAnyRole("USER", "ADMIN")
                .anyRequest()
                .authenticated().and()
                .formLogin().and()
                .logout()
                .and()
                .httpBasic();
/*        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**", "/h2-console/**", "/patient/**","/dentist/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().formLogin()
                .defaultSuccessUrl("/home")
                .and().logout()
                .and().csrf().ignoringAntMatchers("/h2-console/**")
                .and().headers().frameOptions().sameOrigin()
                .and().httpBasic();*/

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return provider;
    }

}
