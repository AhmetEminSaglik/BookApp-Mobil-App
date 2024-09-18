package org.ahmeteminsaglik.bookapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingOL {

    private Summary summary;

    @Override
    public String toString() {
        return "RatingOL{" +
                "summary=" + summary +
                '}';
    }
}
