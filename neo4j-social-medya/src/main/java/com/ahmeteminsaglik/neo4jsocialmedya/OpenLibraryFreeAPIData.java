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
    //    private final static int startIndex = 45804;
    private final static int startIndex = 52257;
    private final static int[] successAddressArr = {45804, 45808, 45809, 45830, 45864, 45868, 45876, 45884, 45887, 45922, 46080, 46089, 46092, 46101, 46103, 46107, 46119, 46154, 46155, 46172, 46176, 46181, 46195, 46201, 46204, 46212, 46236, 46259, 46279, 46290, 46316, 46324, 46327, 46328, 46343, 46347, 46375, 46384, 46385, 46395, 46472, 46760, 46873, 46874, 46875, 46904, 46913, 46932, 47292, 47500, 47529, 47765, 47802, 47816, 47818, 47827, 47840, 47847, 47852, 47863, 47889, 47890, 47891, 47895, 47914, 47922, 47943, 47946, 48020, 48036, 48045, 48054, 48067, 48233, 48540, 48670, 48704, 49494, 49517, 49642, 49652, 49666, 50232, 50283, 50453, 50549, 50550, 50567, 50843, 50859, 51351, 51543, 51655, 51805, 51919, 52114, 52202, 52227, 52252, 52257};
    private final static ObjectMapper objectMapper = new ObjectMapper();
    //    String apiUrl = "https://openlibrary.org/works/12/ratings.json"; // API URL
    private static final CustomLog log = new CustomLog(InitialDataLoader.class);
    private static final List<Author> authorList = new ArrayList<>();
    private final String apiPrefixBook = "https://openlibrary.org/works/OL";
    private final String apiPrefixAuthor = "https://openlibrary.org/authors/";
    private final String apiInfix = "W";
    private final String apiSuffix = ".json";
    private final String apiForbook = "";
    private final String apiForRating = "/ratings";
    private final String apiForEditions = "/editions";
    private final String apiForReadData = "/bookshelves";
    private final RestTemplate restTemplate = new RestTemplate();
    private final Map<String, List<Book>> map = new HashMap<>();

    public Map<String, List<Book>> getBookListMap() {
        return map;
    }

    public List<Author> getAuthorList() {
        return new ArrayList<>(new HashSet<>(authorList));
    }

    public void createBookData(int range) {
        if (range < 10) {
            log.warn("Range min value limit is 10. Range is updated from " + range + " to 10");
            range = 10;
        }
        if (range > 100) {
            log.warn("Range max value limit is 100. Range is updated from " + range + " to 100");
            range = 100;
        }
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
        for (int i = 0; i < range; i++) {

            try {
                log.info((i + 1) + "-) [" + bookList.size() + "/" + range + "] Processing Index : " + (i + 1));
                bookUrl = demoMain.createBookUrl(successAddressArr[i]);
                bookRatingUrl = demoMain.createBookRatingUrl(successAddressArr[i]);
                bookReadDataUrl = demoMain.createBookReadDataUrl(successAddressArr[i]);
                bookEditionUrl = demoMain.createBookEditionUrl(successAddressArr[i]);


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
                authorOL = demoMain.parseJsonToAuthorData(authorDataJson);
                RatingOL ratingOL = demoMain.parseJsonToSummaryOL(bookRatingJson);

                ReadDataOL readDataOL = demoMain.parseJsonToReadDataOL(bookReadDataJson);
                Book book = demoMain.createBookFromAPIData(bookOL, ratingOL, readDataOL);
                Author author = demoMain.createAuthorFromAPIData(authorOL);
                addBookToAuthorKeyMap(book, author);
                bookList.add(book);
                log.info("[->] SUCCESSFUL: " + i);
            } catch (Exception e) {
                log.info("[->] FAILED: " + i);
            }
        }
        log.info("BookList size : " + bookList.size());
        for (Book book : bookList) {
            log.info("point : " + book.getPoint() + "title : " + book.getName() + " / img : " + book.getImgUrl());
        }
    }

    private void addBookToAuthorKeyMap(Book book, Author author) {
        List<Book> bookList = map.get(author.getKey());
        if (bookList == null) {
            bookList = new ArrayList<>();
            bookList.add(book);
            map.put(author.getKey(), bookList);
        } else {
            bookList.add(book);
        }
    }

    private Book createBookFromAPIData(BookOL bookOL, RatingOL ratingOL, ReadDataOL readDataOL) throws Exception {
        Book book = new Book();
        book.setName(bookOL.getTitle());
        book.setImgUrl(bookOL.getImgUrl());
        if (bookOL.getDescription() == null) {
            throw new Exception("Description is null");
        }
        book.setDescription(bookOL.getDescription());
        book.setPoint(ratingOL.getSummary().getAverage() / 2);
        book.setTotalRead(ratingOL.getSummary().getCount());
        book.setIsbn(bookOL.getIsbn());
        book.setWebUrl(bookOL.getWebUrl());
        int totalRead = readDataOL.getCounts().getAlready_read() + readDataOL.getCounts().getCurrently_reading();
        book.setTotalRead(totalRead);
        return book;
    }

    private Author createAuthorFromAPIData(AuthorOL authorOL) {
        Author author = new Author();
        String[] arrKey = authorOL.getKey().split("/");
        author.setKey(arrKey[arrKey.length - 1]);
        String[] arr = authorOL.getName().split(" ");
        String name = "";
        String lastname = arr[arr.length - 1];
        for (int i = 0; i < arr.length - 1; i++) {
            name += arr[i] + " ";
        }
        name = name.trim();

        author.setName(name);
        author.setLastname(lastname);
        authorList.add(author);
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
        BookOL bookOL = objectMapper.readValue(json, BookOL.class);
        bookOL.setWebUrl(bookUrl);
        return bookOL;
    }

    private BookOL parseJsonToBookOLEdition(BookOL bookOL, String json) throws JsonProcessingException {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        JsonNode rootNode = objectMapper.readTree(json);
        JsonNode isbn_10_JN = rootNode.get("entries").get(0).get("isbn_10");
        JsonNode isbn_13_JN = rootNode.get("entries").get(0).get("isbn_13");
        if (isbn_10_JN != null) {
            bookOL.setIsbn(isbn_10_JN.get(0).asText());
        } else if (isbn_13_JN != null) {
            bookOL.setIsbn(isbn_13_JN.get(0).asText());
        }
        return bookOL;
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
        }
        String[] arr = authorKey.split("/");
        authorKey = arr[arr.length - 1];
        AuthorOL authorOL = new AuthorOL();
        authorOL.setKey(authorKey);
        return authorOL;
    }

    private AuthorOL parseJsonToAuthorData(/*AuthorOL authorOL,*/ String json) throws JsonProcessingException {
        AuthorOL authorOL = objectMapper.readValue(json, AuthorOL.class);
        return authorOL;
    }


    private RatingOL parseJsonToSummaryOL(String json) throws JsonProcessingException {
        RatingOL ratingOL;
        ratingOL = objectMapper.readValue(json, RatingOL.class);
        return ratingOL;
    }

    private ReadDataOL parseJsonToReadDataOL(String json) throws JsonProcessingException {
        ReadDataOL readData;
        readData = objectMapper.readValue(json, ReadDataOL.class);
        return readData;
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
}

