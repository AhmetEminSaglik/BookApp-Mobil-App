package com.ahmeteminsaglik.neo4jsocialmedya;

import com.ahmeteminsaglik.neo4jsocialmedya.business.conretes.InitialDataLoader;
import com.ahmeteminsaglik.neo4jsocialmedya.model.*;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class OpenLibraryFreeAPIData {
    //    String apiUrl = "https://openlibrary.org/works/12/ratings.json"; // API URL
    private static CustomLog log = new CustomLog(InitialDataLoader.class);
    private RestTemplate restTemplate = new RestTemplate();
    private final static int startIndex = 45804;
    //    private final static int startIndex = 45904;
    private final String apiPrefixBook = "https://openlibrary.org/works/OL";
    private final String apiPrefixAuthor = "https://openlibrary.org/authors/";
    private final String apiInfix = "W";
    private final String apiSuffix = ".json";
    private final String apiForbook = "";
    private final String apiForRating = "/ratings";
    private final String apiForEditions = "/editions";
    private final String apiForReadData = "/bookshelves";
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private Map<String, List<Book>> map = new HashMap<>();
    private static List<Author> authorList = new ArrayList<>();

    /* public static void main(String[] args) {
         new OpenLibraryFreeAPIData().retrieveData(10);
     }*/

    public Map<String, List<Book>> getBookListMap() {
        return map;
    }

    public List<Author> getAuthorList() {
        System.out.println("Normalde author List size: " + authorList.size());
        System.out.println("hashset size : " + new HashSet<>(authorList).size());
        System.out.println("tekrar arraylist'e donen hashset size : " + new HashSet<>(authorList).size());
        return new ArrayList<>(new HashSet<>(authorList));
    }

    public void createBookData(int range) {
        OpenLibraryFreeAPIData demoMain = new OpenLibraryFreeAPIData();
        List<Book> bookList = new ArrayList<>();
        String bookUrl;
        String bookRatingUrl;
        String bookReadDataUrl;
        String authorDataUrl;
        String bookEditionUrl;
        String bookImgUrl;

        String bookJson;
        String bookRatingJson;
        String bookReadDataJson;
        String authorDataJson;
        String bookEditionJson;
        String bookImgJson;
        for (int i = startIndex; i < startIndex + range; i++) {
            try {
                log.info("[" + (i + 1 - startIndex) + "/" + range + "] Processing Index : " + i);
                bookUrl = demoMain.createBookUrl(i);
                bookRatingUrl = demoMain.createBookRatingUrl(i);
                bookReadDataUrl = demoMain.createBookReadDataUrl(i);
                bookEditionUrl = demoMain.createBookEditionUrl(i);


                bookJson = demoMain.sendGetRequest(bookUrl);
                bookRatingJson = demoMain.sendGetRequest(bookRatingUrl);
                bookReadDataJson = demoMain.sendGetRequest(bookReadDataUrl);
                bookEditionJson = demoMain.sendGetRequest(bookEditionUrl);

                BookOL bookOL = demoMain.parseJsonToBookOL(bookJson, bookUrl);
                if (bookOL == null || bookOL.getTitle() == null) {
                    continue;
                }

                bookOL = demoMain.parseJsonToBookOLEdition(bookOL, bookEditionJson);
                bookImgUrl = demoMain.createBookImgUrl(bookOL.getIsbn());
                bookImgJson = demoMain.sendGetRequest(bookImgUrl);
                bookOL = demoMain.parseJsonToBookImg(bookOL, bookImgJson);


                AuthorOL authorOL = demoMain.parseJsonToAuthorOL(bookJson);
                authorDataUrl = demoMain.createAuthorDataUrl(authorOL.getKey());
                authorDataJson = demoMain.sendGetRequest(authorDataUrl);
                authorOL = demoMain.parseJsonToAuthorData(authorOL, authorDataJson);
                RatingOL ratingOL = demoMain.parseJsonToSummaryOL(bookRatingJson);

                ReadDataOL readDataOL = demoMain.parseJsonToReadDataOL(bookReadDataJson);
                Book book = demoMain.createBookFromAPIData(bookOL, ratingOL, readDataOL);
                Author author = demoMain.createAuthorFromAPIData(authorOL);
                addBookToAuthorKeyMap(book, author);
//            log.info("CREATED BOOK DATA : " + book);
//            log.info("CREATED AUTHOR DATA : " + author);
//            log.info(" URL " + bookUrl);
//            log.info(" URL " + bookRatingUrl);
//            log.info(" URL " + bookReadDataUrl);
//            log.info(" URL " + bookEditionUrl);
//            log.info(" URL " + bookEditionUrl);
//            log.info(" URL " + authorDataUrl);
                bookList.add(book);
                log.info("[->] SUCCESSFUL: " + i);
            } catch (Exception e) {
                log.info("[->] FAILED: " + i);
            }
        }
        log.info("BookList size : " + bookList.size());
        for (Book book : bookList) {
            log.info("title : " + book.getName() + " / img : " + book.getImgUrl());
        }
//        return map;
    }

    private void addBookToAuthorKeyMap(Book book, Author author) {
        List<Book> bookList = map.get(author.getKey());
//        System.out.println("Author key :" + author);
        if (bookList == null) {
            bookList = new ArrayList<>();
            bookList.add(book);
            map.put(author.getKey(), bookList);
//            System.out.println("map.keySet().size() : " + map.keySet().size());
//            System.out.println("map.size() : " + map.size());

        } else {
            bookList.add(book);
//            System.out.println("Mapteki booklist sayisi en az 2 olmali: " + bookList.size());
        }

    }


    private Book createBookFromAPIData(BookOL bookOL, RatingOL ratingOL, ReadDataOL readDataOL) throws Exception {
        Book book = new Book();
        book.setName(bookOL.getTitle());
        book.setImgUrl(bookOL.getImgUrl());
//        if (bookOL.getDescription() == null) {
//            throw new Exception("Description is null");
//        }
        book.setDescription(bookOL.getDescription());
        book.setPoint(ratingOL.getSummary().getAverage());
        book.setTotalRead(ratingOL.getSummary().getCount());
        book.setIsbn_13(bookOL.getIsbn());
        book.setWebUrl(bookOL.getWebUrl());
        int totalRead = readDataOL.getCounts().getAlready_read() + readDataOL.getCounts().getCurrently_reading();
        book.setTotalRead(totalRead);
        return book;
    }

    private Author createAuthorFromAPIData(AuthorOL authorOL) {
        Author author = new Author();
        String arrKey[] = authorOL.getKey().split("/");
//        System.out.println("arrKey.length --> "+arrKey.length+ " > "+arrKey[arrKey.length-1]);
        author.setKey(arrKey[arrKey.length - 1]);

        String arr[] = authorOL.getName().split(" ");
        String name = "";
        String lastname = arr[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            name += arr[i] + " ";
        }
        name = name.trim();

        author.setName(name);
        author.setLastname(lastname);
        System.out.println("------------authorList : " + authorList.size());
        authorList.add(author);
        System.out.println("------------authorList : " + authorList.size());

        return author;
    }

    private String createBookUrl(int index) {
        String bookUrl = apiPrefixBook + index + apiInfix + apiForbook + apiSuffix;
        return bookUrl;
    }

    private String createBookRatingUrl(int index) {
        String bookUrl = apiPrefixBook + index + apiInfix + apiForRating + apiSuffix;
        return bookUrl;
    }

    private String createBookReadDataUrl(int index) {
        String bookUrl = apiPrefixBook + index + apiInfix + apiForReadData + apiSuffix;
        return bookUrl;
    }

    private String createBookEditionUrl(int index) {
        String bookUrl = apiPrefixBook + index + apiInfix + apiForEditions + apiSuffix;
        return bookUrl;
    }

    private String createBookImgUrl(String isbnNo) {
        String bookUrl = "https://openlibrary.org/api/books?bibkeys=ISBN:" + isbnNo + "&callback=mycallback";
        return bookUrl;
    }

    private String createAuthorDataUrl(String authorKey) {
        String bookUrl = apiPrefixAuthor + authorKey + apiSuffix;
        return bookUrl;
    }

    private BookOL parseJsonToBookOL(String json, String bookUrl) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        try {
        BookOL bookOL = objectMapper.readValue(json, BookOL.class);
        bookOL.setWebUrl(bookUrl);
        return bookOL;
//        } catch (JsonProcessingException e) {
//
//            log.error("parseJsonToBookOL parseJsonToBookOL parseJsonToBookOL  : " + e.getMessage());
//        }
//        return null;
    }

    private BookOL parseJsonToBookOLEdition(BookOL bookOL, String json) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        try {
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode isbn_10_JN = rootNode.get("entries").get(0).get("isbn_10");
        JsonNode isbn_13_JN = rootNode.get("entries").get(0).get("isbn_13");//.get(0).asText();
        /*JsonNode description = rootNode.get("entries").get(0).get("description");//.get(0).asText();
        if (description != null) {
            System.out.println("description : " + description.asText());
            bookOL.setDescription(description.asText());
        }*/
/*        JsonNode description2 = rootNode.get("entries").get(0).path("description").path("value");
        if (description2 != null) {
            System.out.println("description2 : " + description2);
            bookOL.setDescription(description.asText());
        }*/
//        JsonNode imgUrl = rootNode.get("entries").get(0).get("covers");//.get(0).asText();
//        System.out.println(">>>>>>>>>>>>>>>> ImggUrl : "+imgUrl);
        if (isbn_10_JN != null) {
            bookOL.setIsbn(isbn_10_JN.get(0).asText());
        } else if (isbn_13_JN != null) {
            bookOL.setIsbn(isbn_13_JN.get(0).asText());
        }

        return bookOL;
//        } catch (Exception e) {
//            log.error("ERROR OCCURED : " + e.getMessage());
//        }
//        log.error(" Image is not Found");
//        return bookOL;
    }

    private BookOL parseJsonToBookImg(BookOL bookOL, String json) throws Exception {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String deleteWord = "mycallback\\(";
        json = json.replaceFirst(deleteWord, "");
        json = json.replace(");", " ");
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            JsonNode bookNode = rootNode.get("ISBN:" + bookOL.getIsbn());
            String thumbnailUrl = null;
            try {
                thumbnailUrl = bookNode.get("thumbnail_url").asText();
            } catch (Exception e) {
                log.error("Error occured; " + e.getMessage());
                throw new Exception();
            }
            String previewUrl = bookNode.get("preview_url").asText();
            // URL'leri kullanabilirsiniz
            if (thumbnailUrl != null && !thumbnailUrl.isEmpty()) {
                bookOL.setImgUrl(thumbnailUrl);
            } else if (previewUrl != null && !previewUrl.isEmpty()) {
                bookOL.setImgUrl(previewUrl);
            } else {
                log.info("Could not find any img url.");
            }
        } catch (JsonProcessingException e) {
            log.error("BookOL parse has been failed : " + e.getMessage());
        }
        return bookOL;
    }


    private AuthorOL parseJsonToAuthorOL(String json) throws JsonProcessingException {
//        try {
        String authorKey = null;
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode authorsNode = rootNode.get("authors");
        if (authorsNode.isArray() && authorsNode.size() > 0) {
            JsonNode authorNode = authorsNode.get(0).get("author");
            if (authorNode != null && authorNode.has("key")) {
                authorKey = authorNode.get("key").asText();
            }
        } else {
//            System.out.println("Authors array not found or empty.");
        }

        String arr[] = authorKey.split("/");
        authorKey = arr[arr.length - 1];

        AuthorOL authorOL = new AuthorOL();
        authorOL.setKey(authorKey);
        return authorOL;
//        } catch (Exception e) {
//            log.error(" ERROR OCCURED >> parseJsonToAuthorOL >> : " + e.getMessage());
//        }
//        return null;

    }

    private AuthorOL parseJsonToAuthorData(AuthorOL authorOL, String json) throws JsonProcessingException {
//        try {
        authorOL = objectMapper.readValue(json, AuthorOL.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return authorOL;
    }


    private RatingOL parseJsonToSummaryOL(String json) throws JsonProcessingException {
        RatingOL ratingOL;
//        Summary summary;
//        try {
        ratingOL = objectMapper.readValue(json, RatingOL.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return ratingOL;
    }

    private ReadDataOL parseJsonToReadDataOL(String json) throws JsonProcessingException {
        ReadDataOL readData;
//        try {
        readData = objectMapper.readValue(json, ReadDataOL.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
        return readData;
    }

    private Book convertBookOLToBook(BookOL bookOL) {
        Book book = new Book();
        book.setName(bookOL.getTitle());
        return book;
    }

    private String sendGetRequest(String apiUrl) {
//        try {
        ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            log.error("Error occured, FreeApi Response Failed");
        }
        return "";
    }
//        catch (Exception e) {
//            log.error("404 Not Found");
//        }
//        return "";
}

