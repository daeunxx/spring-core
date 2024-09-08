package org.example.hellospring;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DataConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).build();
  }

  //JPA 설정
 /* @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
    emf.setDataSource(dataSource());
    emf.setPackagesToScan("org.example.hellospring");
    emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter() {{
      setDatabase(Database.H2);
      setGenerateDdl(true);
      setShowSql(true);
    }});
    return emf;
  }

  @Bean
  public BeanPostProcessor persistenceAnnotationBeanPostProcessor() {
    return new PersistenceAnnotationBeanPostProcessor();
  }

  @Bean
  public PlatformTransactionManager jpaTransactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }
*/
  @Bean
  public PlatformTransactionManager jdbcTransactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }
}
