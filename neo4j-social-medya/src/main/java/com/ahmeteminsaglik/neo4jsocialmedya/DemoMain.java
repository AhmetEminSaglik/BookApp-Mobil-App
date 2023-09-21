package com.ahmeteminsaglik.neo4jsocialmedya;

import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.InitialDataLoader;
import com.ahmeteminsaglik.neo4jsocialmedya.model.*;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DemoMain {
    //    String apiUrl = "https://openlibrary.org/works/12/ratings.json"; // API URL
    private static CustomLog log = new CustomLog(InitialDataLoader.class);
    private RestTemplate restTemplate = new RestTemplate();
    private final static int startIndex = 45804;
    private final String apiPrefix = "https://openlibrary.org/works/OL";
    private final String apiInfix = "W";
    private final String apiSuffix = ".json";
    private final String apiForbook = "";
    private final String apiForRating = "/ratings";
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public static void main(String[] args) {
        DemoMain demoMain = new DemoMain();
        int range = 1;
        String bookUrl;
        String bookJson;
        String bookRatingUrl;
        String bookRatingJson;
        for (int i = startIndex; i < startIndex + range; i++) {
            bookUrl = demoMain.createBookUrl(i);
            bookJson = demoMain.sendGetRequest(bookUrl);

            bookRatingUrl = demoMain.createBookRatingUrl(i);
            bookRatingJson = demoMain.sendGetRequest(bookRatingUrl);
            BookOL bookOL = demoMain.parseJsonToBookOL(bookJson);


            if (bookOL == null) {
                continue;
            } else {
                log.info("bookOL : " + bookOL);
            }

            AuthorOL authorOL = demoMain.parseJsonToAuthorOL(bookJson);
            log.info("Author Key : " + authorOL);


            Rating summaryOL = demoMain.parseJsonToSummaryOL(bookRatingJson);
            log.info("summaryOL :  " + summaryOL);


        }

        /*
         * retrieve book
         * retrieve rating
         * retrieve author
         *
         * */
    }
//    public void saveInitilizateData() {
//        retrieveBookDataFromOpenLibrary();
//    }

    private String createBookUrl(int index) {
        String bookUrl = apiPrefix + index + apiInfix + apiForbook + apiSuffix;
        return bookUrl;
    }

    private String createBookRatingUrl(int index) {
        String bookUrl = apiPrefix + index + apiInfix + apiForRating + apiSuffix;
        return bookUrl;
    }

/*    private BookOL retrieveBookOL(String url) {
        String bookUrl;
//        int range = 10;
//        List<BookOL> listBookOL = new ArrayList<>();
//        for (int i = startIndex; i < startIndex + range; i++) {
//        bookUrl = apiPrefix + index + apiInfix + apiForbook + apiSuffix;
        String json = sendGetRequest(bookUrl);
        try {

            BookOL bookOL = parseJsonToBookOL(json);
            if (!bookOL.getTitle().isEmpty()) {
                log.info("(" + index + ")Parsed BookOL successfully  : " + bookOL);
            } else {
                log.info("Parsed Failed  JSON : " + json);
            }
//                listBookOL.add(bookOL);
            return bookOL;
        } catch (Exception e) {
            log.error("Exception Occured : INDEX :(" + i + ")" + e.getMessage() + " \n Error : JSON : " + json);
        }
        return null;
//        }
//        List<BookOL> bookOLList = bookController.saveAllBookOL(listBookOL).getBody().getData();
//        log.info("Saved BookOL List : ");
//        bookOLList.forEach(e -> log.info(e.toString()));
    }*/


    private BookOL parseJsonToBookOL(String json) {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            BookOL bookOL = objectMapper.readValue(json, BookOL.class);
            return bookOL;
        } catch (JsonProcessingException e) {
            log.error("BookOL parse has been failed : " + e.getMessage());
        }
        return null;
    }

    private AuthorOL parseJsonToAuthorOL(String json) {
        AuthorOL authorOL = null;
        try {
            log.info("Author Json : " + json);

            authorOL = objectMapper.readValue(json, AuthorOL.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        String arr[] = authorOL.getKey().split("/");
        authorOL.setKey(arr[arr.length - 1]);
        return authorOL;
    }

    private Rating parseJsonToSummaryOL(String json) {
        Rating rating;
        Summary summary;
        log.info("Rating Json : " + json);
        try {
            rating = objectMapper.readValue(json, Rating.class);
            log.info("rating  : ObjectManager ....  : " + rating);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return rating;
    }

    private Book convertBookOLToBook(BookOL bookOL) {
        Book book = new Book();
        book.setName(bookOL.getTitle());
        return book;
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
    /*   public static void main(String[] args) {
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
    }*/
}