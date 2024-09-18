package org.ahmeteminsaglik.bookapp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;

@Getter
@Setter
public class BookOL {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String description;
    private String isbn;
    private String imgUrl;
    private String webUrl;
    private String authorKey;

    @Override
    public String toString() {
        return "BookOL{" +
                "\nid='" + id +
                ",\ntitle='" + title +
                ",\ndescription='" + description +
                ",\nimgUrl=" + imgUrl +
                ",\nisbn=" + isbn +
                ",\nwebUrl=" + webUrl +
                ",\nauthorKey=" + authorKey +
                '}';
    }

    public void setImgUrl(String imgUrl) {
        imgUrl = imgUrl.replace("-S", "");
        this.imgUrl = imgUrl;
    }


}
