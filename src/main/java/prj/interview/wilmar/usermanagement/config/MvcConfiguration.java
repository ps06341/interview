package prj.interview.wilmar.usermanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = "prj.interview.wilmar.usermanagement")
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {
//	@Bean
//	public HandlerMapping resourceHandlerMapping() {
//		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
//
//		Map<String, Object> urlMap = new HashMap<>();
//		simpleUrlHandlerMapping.setUrlMap(urlMap);
//
//		return simpleUrlHandlerMapping;
//	}
//
//	@Bean
//	public HandlerMapping defaultServletHandlerMapping() {
//		return resourceHandlerMapping();
//	}

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
}
