package com.github.florinn.samples.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ComponentScan({"com.github.florinn.samples.security"})
@ImportResource("classpath:spring-context.xml")
public class SpringJavaBasedConfiguration {

}
