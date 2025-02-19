package com.topjava.webapp.model.sections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Link implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String name;
    private String url;

    public Link() {
    }
    public Link(String name, String url) {
        Objects.requireNonNull(name, "Name can't been null.");
        this.name = name;
        this.url = url == null ? "" : url;
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Link {"+name+", "+url+"}";
    }
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return name.equals(link.name) && url.equals(link.url);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, url);
    }
}
