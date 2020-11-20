package de.ksbrwsk.xml;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@ConfigurationProperties(prefix = "xml", ignoreUnknownFields = true)
@Component
public class XmlApplicationProperties {
    private String pathIn;
    private String pathOut;
}
