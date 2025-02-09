package com.topjava.webapp.model.sections;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class AboutOrganizationSection extends Sections {
    @Serial
    private static final long serialVersionUID = 1L;

    public AboutOrganizationSection() {
    }

    private List<AboutOrganization> organizations;

    public AboutOrganizationSection(List<AboutOrganization> organizations) {
        Objects.requireNonNull(organizations, "Organization list can't be null.");
        this.organizations = organizations;
    }
    public AboutOrganizationSection(AboutOrganization... items) {
        this(Arrays.asList(items));
    }

    public List<AboutOrganization> getOrganizations() {
        return organizations;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AboutOrganizationSection that = (AboutOrganizationSection) o;
        return Objects.equals(organizations, that.organizations);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(organizations);
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
