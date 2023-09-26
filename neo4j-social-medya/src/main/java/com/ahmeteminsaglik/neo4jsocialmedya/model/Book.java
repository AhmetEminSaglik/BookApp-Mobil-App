package com.ahmeteminsaglik.neo4jsocialmedya.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

import java.text.DecimalFormat;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double point;
    private String imgUrl;
    private int totalRead;
    private String description;
    private String isbn;
    private String webUrl;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", imgUrl='" + imgUrl + '\'' +
                ", totalRead=" + totalRead +
                ", description='" + description + '\'' +
                ", isbn='" + isbn + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }

    public void setPoint(double point) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setMaximumFractionDigits(1);
        this.point = Double.parseDouble(decimalFormat.format(point));
    }

    public void setDescription(String description) {
        if (description != null) {
            String[] descArr = description.split("Contains");
            this.description = descArr[0];
        }
    }
}
