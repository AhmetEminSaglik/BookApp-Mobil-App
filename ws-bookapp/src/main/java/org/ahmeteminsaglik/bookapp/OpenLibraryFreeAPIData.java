package org.ahmeteminsaglik.bookapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.ahmeteminsaglik.ReadableFormat;
import org.ahmeteminsaglik.bookapp.model.*;
import org.ahmeteminsaglik.bookapp.utility.CustomLog;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class OpenLibraryFreeAPIData {
    private final static int startIndex = 52257;
    private final static ObjectMapper objectMapper = new ObjectMapper();
    //    String apiUrl = "https://openlibrary.org/works/12/ratings.json"; // API URL
    private static final CustomLog log = new CustomLog(OpenLibraryFreeAPIData.class);
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
        HashSet<Author> set = new HashSet<>(authorList);
        return new ArrayList<>(set);
    }

    public void createBookData() {
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

        List<String> urlIndexList = getBookUrlIndexList();
        for (int i = 0; i < 20; i++) {
            try {
                int processUrlIndex = Integer.parseInt(urlIndexList.get(i));
                log.info((i + 1) + "-) [" + bookList.size() + "/" + urlIndexList.size() + "] Processing Index : " + (ReadableFormat.getStringValue(processUrlIndex)));
                bookUrl = demoMain.createBookUrl(processUrlIndex);
                bookRatingUrl = demoMain.createBookRatingUrl(processUrlIndex);
                bookReadDataUrl = demoMain.createBookReadDataUrl(processUrlIndex);
                bookEditionUrl = demoMain.createBookEditionUrl(processUrlIndex);

                bookJson = demoMain.sendGetRequest(bookUrl);
                bookRatingJson = demoMain.sendGetRequest(bookRatingUrl);
                bookReadDataJson = demoMain.sendGetRequest(bookReadDataUrl);
                bookEditionJson = demoMain.sendGetRequest(bookEditionUrl);

                BookOL bookOL = demoMain.parseJsonToBookOL(bookJson, bookUrl);
                if (bookOL.getTitle() == null) {
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
                log.info("[->] FAILED: " + i + " --> " + e.getMessage());
            }
        }
        log.info("BookList size : " + bookList.size());
        for (Book book : bookList) {
            log.info("point : " + book.getPoint() + "title : " + book.getName() + " / img : " + book.getImgUrl());
        }
    }

    private List<String> getBookUrlIndexList() {
        List<String> list = new ArrayList<>();
        try {
            ClassPathResource resource = new ClassPathResource("indexes.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
                list.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
        /*
        FileFundamental fileFund = new FileFundamental();
        fileFund.setFileName("indexes");
        fileFund.setPath("src\\main\\resources\\");
        fileFund.setFileExtension("txt");
        FileOperationFacade fileOperation = new FileOperationFacade(new WriteFileManagement(fileFund), new ReadFileManagement(fileFund));
        fileOperation.read();
        List<String> list = fileOperation.getReadDataList();
        System.out.println("read data : (size)" + list.size());
        return list;
    */
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

