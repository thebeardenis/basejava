package com.topjava.webapp.model;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.topjava.webapp.util.DateUtil.NOW;
import static com.topjava.webapp.util.DateUtil.of;

public class AboutOrganization {
    private List<Position> directions = new ArrayList<>();
    private final Link linkHomePage;

    public AboutOrganization(String name, String url, List<Position> directions) {
        this.directions = directions;
        this.linkHomePage = new Link(name, url);
    }

    public AboutOrganization(Link homePage, List<Position> directions) {
        this.directions = directions;
        this.linkHomePage = homePage;
    }
    @Override
    public String toString () {
        return "Organization(" + linkHomePage + ", " + directions + ")";
    }

    public static class Position {
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String title;
        private final String description;

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
            this.description = description;
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
            if (this == o) return true;
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