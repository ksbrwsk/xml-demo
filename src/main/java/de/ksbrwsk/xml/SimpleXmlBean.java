package de.ksbrwsk.xml;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SimpleXmlBean {
    private String id;
    private String name;
    private String vorname;
    private List<String> stringList = new ArrayList<>();

    public void addValue(String value) {
        this.stringList.add(value);
    }

    public int countValues() {
        return this.stringList.size();
    }
}
