package com.techcareer.bean;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperBeanClass {
    // Model Mapper
    @Bean
    public ModelMapper modelMapperMethod(){
        return new ModelMapper();
    }
}
