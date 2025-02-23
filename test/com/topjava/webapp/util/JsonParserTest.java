package com.topjava.webapp.util;

import com.topjava.webapp.model.Resume;
import com.topjava.webapp.model.sections.Sections;
import com.topjava.webapp.model.sections.TextSection;
import org.junit.Assert;
import org.junit.Test;

import static com.topjava.webapp.TestData.R1;

public class JsonParserTest {

    @Test
    public void testResume() throws Exception {
        String json = JsonParser.write(R1);
        System.out.println(json);
        Resume r1 = JsonParser.read(json, Resume.class);
        Assert.assertEquals(R1, r1);
    }
    @Test
    public void write() throws Exception{
        Sections sections = new TextSection("Object one");
        String json = JsonParser.write(sections, Sections.class);
        System.out.println(json);
        Sections section2 = JsonParser.read(json, Sections.class);
        Assert.assertEquals(sections, section2);
    }
}
