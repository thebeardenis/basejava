package com.topjava.webapp.model;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class AboutOrganizationSection extends Sections{
    @Serial
    private static final long serialVersionUID = 1L;

    private final List<AboutOrganization> organizations;

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AboutOrganizationSection that = (AboutOrganizationSection) o;

        return organizations.equals(that.organizations);

    }

    @Override
    public int hashCode() {
        return organizations.hashCode();
    }

    @Override
    public String toString() {
        return organizations.toString();
    }
}
