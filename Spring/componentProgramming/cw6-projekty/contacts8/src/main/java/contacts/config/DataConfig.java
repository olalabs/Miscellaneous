package contacts.config;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.IOException;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@ComponentScan
public class DataConfig {

  //komponent - źródło danych jest identyczne, jak w dostępie przez JDBC
  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .addScript("schema.sql") // import schematu bazy danych
            .addScript("data.sql") // import danych testowych
            .build();
  }

  //komponent - fabryka sesji Hibernate
  @Bean
  public SessionFactory sessionFactoryBean() throws IOException {
    Properties props = new Properties();
    props.setProperty("dialect", "org.hibernate.dialect.H2Dialect");

    LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
    sessionFactoryBean.setDataSource(dataSource());
    // nazwa pakietu do wyszukania klas utrwalanych obiektów
    sessionFactoryBean.setPackagesToScan("contacts");
    sessionFactoryBean.setHibernateProperties(props);
    sessionFactoryBean.afterPropertiesSet();

    return sessionFactoryBean.getObject();
  }

  //komponent - menedżer transakcji Hibernat
  @Bean
  public PlatformTransactionManager annotationDrivenTransactionManager() throws IOException {
    HibernateTransactionManager transactionManager = new HibernateTransactionManager();
    transactionManager.setSessionFactory(sessionFactoryBean());
    return transactionManager;
  }
}
