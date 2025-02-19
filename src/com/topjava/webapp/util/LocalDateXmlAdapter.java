package com.topjava.webapp.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDate;

public class LocalDateXmlAdapter extends XmlAdapter<String, LocalDate> {

    @Override
    public LocalDate unmarshal(String str) throws Exception {
        return LocalDate.parse(str);
    }
    @Override
    public String marshal(LocalDate ld) throws Exception {
        return ld.toString();
    }
}
