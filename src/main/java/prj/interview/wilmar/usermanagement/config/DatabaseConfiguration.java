package prj.interview.wilmar.usermanagement.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "prj.interview.wilmar.usermanagement")
//@PropertySource("classpath:db.properties")
public class DatabaseConfiguration implements WebMvcConfigurer {
//	@Autowired
//	private Environment env;

	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactoryBean() {
		LocalContainerEntityManagerFactoryBean lcemfb = new LocalContainerEntityManagerFactoryBean();
		lcemfb.setJpaVendorAdapter(getJpaVendorAdapter());
		lcemfb.setDataSource(getDataSource());
		lcemfb.setPersistenceUnitName("myJpaPersistenceUnit");
		lcemfb.setPackagesToScan("prj.interview.wilmar.usermanagement");
		lcemfb.setJpaProperties(jpaProperties());
		return lcemfb;
	}

	@Bean
	public JpaVendorAdapter getJpaVendorAdapter() {
		JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		return adapter;
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setDriverClassName(env.getProperty("database.driverClassName"));
//		dataSource.setUrl(env.getProperty("database.url"));
//		dataSource.setUsername(env.getProperty("database.username"));
//		dataSource.setPassword(env.getProperty("database.password"));
		dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/wilmar_test");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public PlatformTransactionManager txManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(getEntityManagerFactoryBean().getObject());
		return jpaTransactionManager;
	}

	private Properties jpaProperties() {
		Properties properties = new Properties();
//		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}
}
