package com.ahmeteminsaglik.neo4jsocialmedya;

import com.ahmeteminsaglik.neo4jsocialmedya.model.AuthorOL;
import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class DemoMain {
    //    String apiUrl = "https://openlibrary.org/works/12/ratings.json"; // API URL
    private static CustomLog log = new CustomLog(DemoMain.class);

    static RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
//        int startIndex=18020100;
        int startIndex = 45804;
        int num = 1;

        for (int i = startIndex; i < startIndex + num; i++) {
            String ratingUrl = "https://openlibrary.org/works/OL" + i + "W/ratings.json";
            String bookUrl = "https://openlibrary.org/works/OL" + i + "W.json";
            String bookEditionUrl = "https://openlibrary.org/works/OL" + i + "W/editions.json";
            sendGetRequest(bookUrl);
//            sendGetRequest(bookEditionUrl);
//            sendGetRequest(ratingUrl);

        }
    }


    private static void sendGetRequest(String apiUrl) {
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
//                String data = response.getBody().replaceAll(",", "\n");
                log.info("Retrieved Json Data : "+response.getBody());
                ObjectMapper objectMapper = new ObjectMapper();
                String json = response.getBody();
                List<BookOL> books = new ArrayList<>();
                try {
                    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                    for (String jsonData : json.split("\n")) {
                        BookOL book = objectMapper.readValue(jsonData, BookOL.class);
//                    List<AuthorOL> authors= Collections.singletonList(objectMapper.readValue(bookJson, AuthorOL.class));
                        AuthorOL authorOL = objectMapper.readValue(json, AuthorOL.class);
                        String arr[]=authorOL.getKey().split("/");
                        authorOL.setKey(arr[arr.length-1]);
//                        book.setAuthorOL(authorOL);
                        book.setAuthorKey(authorOL.getKey());
                        books.add(book);
                    }
                } catch (Exception e) {
                    log.error("Error Occured : " + e.getMessage());
                }
                for (BookOL book : books) {
                    log.info("Book : " + book);
                }


            }

        } catch (Exception e) {
//                System.out.println("Null " + e.getMessage());
        }
    }
}