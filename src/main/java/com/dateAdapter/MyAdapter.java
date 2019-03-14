package com.dateAdapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class MyAdapter extends XmlAdapter<String, LocalDate> {

    public LocalDate unmarshal(String v) {
        return LocalDate.parse(v);
    }

    public String marshal(LocalDate v) {
        return v.toString();
    }
}


