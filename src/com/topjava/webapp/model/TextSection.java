package com.topjava.webapp.model;

import java.io.Serial;
import java.util.Objects;

public class TextSection extends Sections{
    @Serial
    private static final long serialVersionUID = 1L;

    private final String textContent;

    public TextSection(String textContent) {
        Objects.requireNonNull(textContent, "Text not can null.");
        this.textContent = textContent;
    }
    public String getTextContent() {
        return textContent;
    }
    @Override
    public String toString() {
        return textContent;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TextSection that = (TextSection) o;
        return textContent.equals(that.textContent);
    }
    @Override
    public int hashCode() {
        return textContent.hashCode();
    }

}
