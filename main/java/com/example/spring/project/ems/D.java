package com.example.spring.project.ems;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

public interface D {
    //System
    void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception;
}
