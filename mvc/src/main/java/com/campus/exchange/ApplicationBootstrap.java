package com.campus.exchange;

import com.campus.exchange.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InjectionPoint;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication(scanBasePackages = {"com.campus.exchange"})
@ServletComponentScan(basePackages = {"com.campus.exchange.filter"})
public class ApplicationBootstrap extends SpringBootServletInitializer {
    public static void main(String[] args){
        SpringApplication.run(ApplicationBootstrap.class, args);

        // Add ContextLoaderListener
        // Then we could comment out the if() statement in SecFilter
    }

}
