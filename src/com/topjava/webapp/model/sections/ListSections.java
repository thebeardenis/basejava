package com.topjava.webapp.model.sections;

import java.io.Serial;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ListSections extends Sections {
    @Serial
    private static final long serialVersionUID = 1L;

    private List<String> items;

    public ListSections() {
    }
    public ListSections(List<String> items) {
        Objects.requireNonNull(items, "List sections can't don't have no items.");
        this.items = items;
    }
    public ListSections(String... items) {
        this(Arrays.asList(items));
    }

    public List<String> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return items.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSections that = (ListSections) o;
        return items.equals(that.items);
    }
    @Override
    public int hashCode() {
        return items.hashCode();
    }
}
