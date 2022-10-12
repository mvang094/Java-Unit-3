package com.javaunit3.springmvc;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {

    @Bean
    public SessionFactory getFactory() {
        SessionFactory factory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(MovieEntity.class) //Add each entity to the session
                .addAnnotatedClass(VoteEntity.class)
                .buildSessionFactory();
                //is deprecated, meaning no longer important and should not be used
        return factory;
    }
}
