package prj.interview.wilmar.usermanagement.config;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import prj.interview.wilmar.usermanagement.services.UserServices;

@Configuration
public class ProcessAfterInitializationConfiguration {

	@Autowired
	protected UserServices userServices;

	@PostConstruct
	private void afterOnStartUp(){
		//first load
		userServices.reloadCaching();
	}
}
