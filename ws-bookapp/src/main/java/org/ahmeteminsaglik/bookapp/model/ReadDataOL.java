package org.ahmeteminsaglik.bookapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReadDataOL {
    CountOL counts;

    @Override
    public String toString() {
        return "ReadDataOL{" +
                "counts=" + counts +
                '}';
    }
}