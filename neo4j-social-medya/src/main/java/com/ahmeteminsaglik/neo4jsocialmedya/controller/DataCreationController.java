package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import org.ahmeteminsaglik.DataCreation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
public class DataCreationController {
    DataCreation dataCreation = new DataCreation();

    @GetMapping
    public String getLinks() {
        String text = "Please run the codes in the links provided in the given order.\n<ul>";
        text += getHTMLLink("create", "create data query");
        text += getHTMLLink("fix-user-data", "fix user data query");
        text += getHTMLLink("fix-book-data", "fix book data query");
        text += getHTMLLink("fix-author-data", "fix author data query");
        text += "</ul>";
        return text;
    }

    @GetMapping("/create")
    public String getRandomNeo4jData() {
        return getHTMLCode(dataCreation.getQueryText().toString());
    }

    @GetMapping("/fix-user-data")
    public String getFixUserDataQuery() {
        String text = dataCreation.getFixUserDataQueryText().toString();
        return getHTMLCode(text);
    }

    @GetMapping("/fix-book-data")
    public String getFixBookDataQuery() {
        String text = dataCreation.getFixBookPointDataQueryText().toString();
        return getHTMLCode(text);
    }

    @GetMapping("/fix-author-data")
    public String getFixAuthorDataQuery() {
        String text = dataCreation.getFixAuthorDataQueryText().toString();
        return getHTMLCode(text);
    }


    private String getHTMLCode(String text) {
        text = text.replaceAll("\n", "<br>");
        return text;
    }

    private String getHTMLLink(String link, String explain) {
        return "<li> <a href=\"http://localhost:8080/data/" + link + "\">" + explain + "</a><br> </li> ";
    }
}
