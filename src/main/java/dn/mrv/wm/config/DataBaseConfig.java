package dn.mrv.wm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("dn.mrv.wm")
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "dn.mrv.wm")
public class DataBaseConfig {

    @Value("${app.datasource.url}")
    private String URL;
    @Value("${app.datasource.driverClassName}")
    private String DRIVER;
    @Value("${app.datasource.username}")
    private String USERNAME;
    @Value("${app.datasource.password}")
    private String PASSWORD;
    @Value("${hibernate.dialect}")
    private String DIALECT;
    @Value("${hibernate.show_sql}")
    private String SHOW_SQL;
    @Value("${hibernate.hbm2ddl.auto}")
    private String HBM2DLL_AUTO;
    @Value("${entitymanager.packagesToScan}")
    private String PACKAGE_TO_SCAN;
    @Value("${hibernate.format_sql}")
    private String FORMAT_SQL;

    @Resource
    private Environment env;

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DRIVER);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setUrl(URL);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory().getObject());
        return transactionManager;
    }

    // To use JPA in a Spring project, the EntityManager needs to be set up
    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean getEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(getDataSource());
        em.setPackagesToScan(new String[]{PACKAGE_TO_SCAN});
        // create jpa properties using Hibernate provider
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", DIALECT);
        hibernateProperties.put("hibernate.show_sql", SHOW_SQL);
        hibernateProperties.put("hibernate.format_sql", FORMAT_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HBM2DLL_AUTO);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(hibernateProperties);
        return em;
    }
}
