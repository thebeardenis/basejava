package com.topjava.webapp.model.sections;

import com.topjava.webapp.util.LocalDateXmlAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.topjava.webapp.util.DateUtil.NOW;
import static com.topjava.webapp.util.DateUtil.of;

@XmlAccessorType(XmlAccessType.FIELD)
public class AboutOrganization implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<Position> directions = new ArrayList<>();
    private Link linkHomePage;

    public AboutOrganization() {}
    public AboutOrganization(String name, String url, List<Position> directions) {
        this.directions = directions;
        this.linkHomePage = new Link(name, url);
    }
    public AboutOrganization(Link homePage, List<Position> directions) {
        this.directions = directions;
        this.linkHomePage = homePage;
    }

    public Link getLinkPage() {
        return linkHomePage;
    }
    public List<Position> getDirections() {
        return directions;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AboutOrganization that = (AboutOrganization) o;
        return Objects.equals(directions, that.directions) && Objects.equals(linkHomePage, that.linkHomePage);
    }
    @Override
    public int hashCode() {
        return Objects.hash(directions, linkHomePage);
    }
    @Override
    public String toString () {
        return "Organization(" + linkHomePage + ", " + directions + ")";
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    public static class Position implements Serializable{
        @Serial
        private static final long serialVersionUID = 1L;

        @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
        private LocalDate startDate;
        @XmlJavaTypeAdapter(LocalDateXmlAdapter.class)
        private LocalDate endDate;
        private String title;
        private String description;

        public Position() {
        }
        public Position(int startYear, Month startMonth, String title, String description) {
            this(of(startYear, startMonth), NOW, title, description);
        }
        public Position(int startYear, Month startMonth, int endYear, Month endMonth, String title, String description) {
            this(of(startYear, startMonth), of(endYear, endMonth), title, description);
        }
        public Position(LocalDate startDate, LocalDate endDate, String title, String description) {
            Objects.requireNonNull(startDate, "startDate must not be null");
            Objects.requireNonNull(endDate, "endDate must not be null");
            Objects.requireNonNull(title, "title must not be null");
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.description = description == null ? "" : description;
        }

        public LocalDate getStartDate() {
            return startDate;
        }
        public LocalDate getEndDate() {
            return endDate;
        }
        public String getTitle() {
            return title;
        }
        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return Objects.equals(startDate, position.startDate) &&
                    Objects.equals(endDate, position.endDate) &&
                    Objects.equals(title, position.title) &&
                    Objects.equals(description, position.description);
        }
        @Override
        public int hashCode() {
            return Objects.hash(startDate, endDate, title, description);
        }
        @Override
        public String toString() {
            return "Position(" + startDate + ',' + endDate + ',' + title + ',' + description + ')';
        }
    }
}