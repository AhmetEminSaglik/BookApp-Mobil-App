package com.ahmeteminsaglik.neo4jsocialmedya.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookInfoISBN {
    @JsonProperty("bib_key")
    private String bibKey;

    @JsonProperty("info_url")
    private String infoUrl;

    @JsonProperty("preview")
    private String preview;

    @JsonProperty("preview_url")
    private String previewUrl;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    @Override
    public String toString() {
        return "BookInfoISBN{" +
                "bibKey='" + bibKey + '\'' +
                ", infoUrl='" + infoUrl + '\'' +
                ", preview='" + preview + '\'' +
                ", previewUrl='" + previewUrl + '\'' +
                ", thumbnailUrl='" + thumbnailUrl + '\'' +
                '}';
    }
}
