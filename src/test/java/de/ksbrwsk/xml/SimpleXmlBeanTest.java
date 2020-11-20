package de.ksbrwsk.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SimpleXmlBeanTest {

    private final static String XML_STRING = "<SimpleXmlBean><id>1</id><name>Name</name><vorname>Vorname</vorname><stringList><stringList>Name1</stringList><stringList>Name2</stringList><stringList>Name3</stringList></stringList></SimpleXmlBean>";

    @Autowired
    XmlApplicationProperties xmlApplicationProperties;

    private SimpleXmlBean createFixture() {
        List<String> stringList = List.of("Name1", "Name2", "Name3");
        SimpleXmlBean bean = new SimpleXmlBean();
        bean.setId("1");
        bean.setName("Name");
        bean.setVorname("Vorname");
        bean.setStringList(stringList);
        return bean;
    }

    @Test
    void createBean() {
        SimpleXmlBean bean = this.createFixture();
        assertEquals(bean.getId(), "1");
        assertEquals(bean.getName(), "Name");
        assertEquals(bean.getVorname(), "Vorname");
        assertEquals(bean.countValues(), 3);
        log.info(bean);
    }

    @Test
    void serializeBeanToString() throws JsonProcessingException {
        SimpleXmlBean bean = this.createFixture();
        XmlMapper mapper = new XmlMapper();
        String actual = mapper.writeValueAsString(bean);
        assertNotNull(actual);
        assertEquals(actual, XML_STRING);
        log.info(actual);
    }

    @Test
    void deserializeBeanFromString() throws JsonProcessingException {
        SimpleXmlBean expected = this.createFixture();
        XmlMapper mapper = new XmlMapper();
        SimpleXmlBean actual = mapper.readValue(XML_STRING, SimpleXmlBean.class);
        assertNotNull(actual);
        assertEquals(actual, expected);
        log.info(actual);
    }

    @Test
    void toFile() throws IOException {
        SimpleXmlBean bean = this.createFixture();
        XmlMapper mapper = new XmlMapper();
        String filename = this.xmlApplicationProperties.getPathOut() + "/SimpleXmlBean.xml";
        mapper.writeValue(new File(filename), bean);
        assertTrue(Files.exists(Path.of(filename)));
        log.info(bean);
    }

    @Test
    void fromFile() throws IOException {
        SimpleXmlBean expected = this.createFixture();
        File file = new ClassPathResource("SimpleXmlBean.xml").getFile();
        XmlMapper mapper = new XmlMapper();
        SimpleXmlBean actual = mapper.readValue(file, SimpleXmlBean.class);
        assertNotNull(actual);
        assertEquals(expected, actual);
    }
}