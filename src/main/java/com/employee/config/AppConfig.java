package com.employee.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.hibernate.HibernateTransactionManager;
import org.springframework.orm.jpa.hibernate.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = "com")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dmds = new DriverManagerDataSource();
        dmds.setUrl("jdbc:mysql://127.0.0.1:3306/spring");
        dmds.setUsername("root");
        dmds.setPassword("root");
        return dmds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sf=new LocalSessionFactoryBean();
        DataSource dataSource = dataSource();
        sf.setDataSource(dataSource);
        sf.setPackagesToScan("com");
        Properties properties = properties();
        sf.setHibernateProperties(properties);
        return sf;
    }

    @Bean
    public Properties properties(){
        Properties pro =new Properties();
        pro.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
//        pro.setProperty("hibernate.show_sql", "true");
        pro.setProperty("hibernate.hbm2ddl.auto", "update");
        return pro;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(SessionFactory sessionFactory){
        HibernateTransactionManager htm= new HibernateTransactionManager(sessionFactory);
        return htm;
    }
}
