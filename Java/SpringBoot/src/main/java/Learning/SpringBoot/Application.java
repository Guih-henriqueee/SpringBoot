package Learning.SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import Learning.Config.DatabaseProperties;



@SpringBootApplication
@EnableConfigurationProperties(DatabaseProperties.class)
@ComponentScan(basePackages = "Learning")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
