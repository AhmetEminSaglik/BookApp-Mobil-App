package org.ahmeteminsaglik.bookapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountOL {
    int want_to_read;
    int currently_reading;
    int already_read;

    @Override
    public String toString() {
        return "Counts{" +
                "want_to_read=" + want_to_read +
                ", currently_reading=" + currently_reading +
                ", already_read=" + already_read +
                '}';
    }
}
