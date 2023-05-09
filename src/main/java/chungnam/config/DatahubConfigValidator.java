package chungnam.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springmodules.validation.commons.DefaultValidatorFactory;

@Configuration
public class DatahubConfigValidator {

	@Bean
	public DefaultBeanValidator beanValidator() throws IOException {
		DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
		defaultBeanValidator.setValidatorFactory(validatorFactory());
		return defaultBeanValidator;
	}

	@Bean
	public DefaultValidatorFactory validatorFactory() throws IOException {
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	    Resource[] resource = resolver.getResources("classpath:/chungnam/validator/*.xml");
	    DefaultValidatorFactory defaultValidatorFactory = new DefaultValidatorFactory();
	    defaultValidatorFactory.setValidationConfigLocations(resource);
		return defaultValidatorFactory;
	}
	
	private Resource[] getValidationConfigLocations() {

		PathMatchingResourcePatternResolver pathMatchingResourcePatternResolver = new PathMatchingResourcePatternResolver();

		List<Resource> validationConfigLocations = new ArrayList<Resource>();

		Resource[] validationRulesConfigLocations = new Resource[] {
			pathMatchingResourcePatternResolver
				.getResource("classpath:/egovframework/validator/validator-rules-let.xml")
		};

		Resource[] validationFormSetLocations = new Resource[] {};
		try {
			validationFormSetLocations = pathMatchingResourcePatternResolver
				.getResources("classpath:/egovframework/validator/let/**/*.xml");
		} catch (IOException e) {
			// TODO Exception 처리 필요
		}

		validationConfigLocations.addAll(Arrays.asList(validationRulesConfigLocations));
		validationConfigLocations.addAll(Arrays.asList(validationFormSetLocations));

		return validationConfigLocations.toArray(new Resource[validationConfigLocations.size()]);
	}

}
