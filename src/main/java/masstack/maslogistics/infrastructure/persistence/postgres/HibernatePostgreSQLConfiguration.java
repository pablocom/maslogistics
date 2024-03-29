package masstack.maslogistics.infrastructure.persistence.postgres;

import masstack.maslogistics.infrastructure.persistence.entities.PacketEntity;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.dialect.PostgreSQL9Dialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernatePostgreSQLConfiguration {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        var sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(PacketEntity.class.getPackage().getName());
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new BasicDataSource();
        dataSource.setDriverClassName(org.postgresql.Driver.class.getPackageName() + "." + org.postgresql.Driver.class.getSimpleName());
        dataSource.setUrl("jdbc:postgresql://localhost/maslogistics");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        var transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private Properties hibernateProperties() {
        var hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty("hibernate.dialect", PostgreSQL9Dialect.class.getCanonicalName());

        return hibernateProperties;
    }
}
