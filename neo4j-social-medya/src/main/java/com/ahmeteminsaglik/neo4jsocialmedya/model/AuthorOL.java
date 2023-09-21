package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Getter
@Setter
public class AuthorOL {
    private Long id;
    private String key;
    @Override
    public String toString() {
        return "AuthorOL{" +
                "key='" + key + '\'' +
                '}';
    }
}
