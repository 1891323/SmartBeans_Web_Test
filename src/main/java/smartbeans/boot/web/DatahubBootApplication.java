package smartbeans.boot.web;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(basePackages= "smartbeans")
@ImportResource("classpath:smartbeans/spring/context-security.xml")
public class DatahubBootApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(DatahubBootApplication.class);
		springApplication.setBannerMode(Banner.Mode.OFF);
		springApplication.setLogStartupInfo(false);
		springApplication.run(args);
	}

}
