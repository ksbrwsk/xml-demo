package de.ksbrwsk.xml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class XmlDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlDemoApplication.class, args);
	}

}
