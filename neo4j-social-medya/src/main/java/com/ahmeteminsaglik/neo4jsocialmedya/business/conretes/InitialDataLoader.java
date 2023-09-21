package com.ahmeteminsaglik.neo4jsocialmedya.business.conretes;

import com.ahmeteminsaglik.neo4jsocialmedya.controller.BookController;
import com.ahmeteminsaglik.neo4jsocialmedya.model.AuthorOL;
import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialDataLoader implements CommandLineRunner {
    private static CustomLog log = new CustomLog(InitialDataLoader.class);
    private RestTemplate restTemplate = new RestTemplate();
    private final static int startIndex = 45804;
    private final String apiPrefix = "https://openlibrary.org/works/OL";
    private final String apiInfix = "W";
    private final String apiSuffix = ".json";
    private final String apiForbook = "";

    @Autowired
    BookController bookController;

    @Override
    public void run(String... args) throws Exception {
        saveInitilizateData();
    }

    public void saveInitilizateData() {
        retrieveBookDataFromOpenLibrary();
    }

    private void retrieveBookDataFromOpenLibrary() {
        String bookUrl;
        int range = 10;
        List<BookOL> listBookOL = new ArrayList<>();
        for (int i = startIndex; i < startIndex + range; i++) {
            log.info("Working on : "+i);
            bookUrl = apiPrefix + i + apiInfix + apiForbook + apiSuffix;
            String json = sendGetRequest(bookUrl);
            try {

                BookOL bookOL = parseJsonToBookOL(json);
                if (!bookOL.getTitle().isEmpty()) {
                    log.info("(" + i + ")Parsed BookOL successfully  : " + bookOL);
                    bookOL= bookController.save(bookOL).getBody().getData();
                    log.info("("+i+")SAVED BOOK : id : "+bookOL.getId()+", title : "+bookOL.getTitle());

                }else{
                    log.info("Parsed Failed  JSON : "+json);
                }
//                listBookOL.add(bookOL);
            } catch (Exception e) {
//                log.error("Exception Occured : INDEX :(" + i + ")" + e.getMessage() + " \n Error : JSON : "+json);
            }
        }
//        List<BookOL> bookOLList = bookController.saveAllBookOL(listBookOL).getBody().getData();
//        log.info("Saved BookOL List : ");
//        bookOLList.forEach(e -> log.info(e.toString()));
        log.info("All Book Data is retrieved and saved to Neo4j DB");
    }


    private BookOL parseJsonToBookOL(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            BookOL bookOL = objectMapper.readValue(json, BookOL.class);
            AuthorOL authorOL = objectMapper.readValue(json, AuthorOL.class);
            String arr[] = authorOL.getKey().split("/");
            authorOL.setKey(arr[arr.length - 1]);
//            bookOL.setAuthorOL(authorOL);
            bookOL.setAuthorKey(authorOL.getKey());
            return bookOL;
        } catch (JsonProcessingException e) {
//            log.error("BookOL parse has been failed : "+e.getMessage());
//            System.exit(0);
        }
        return null;
    }

    private String sendGetRequest(String apiUrl) {
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            log.error("Error occured, FreeApi Response Failed");
        }
        return "";
    }
}
