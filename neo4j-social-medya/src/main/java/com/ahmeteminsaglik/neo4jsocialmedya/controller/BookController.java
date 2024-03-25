package com.ahmeteminsaglik.neo4jsocialmedya.controller;

import com.ahmeteminsaglik.neo4jsocialmedya.business.abstracts.BookService;
import com.ahmeteminsaglik.neo4jsocialmedya.mapper.UserMapper;
import com.ahmeteminsaglik.neo4jsocialmedya.model.Book;
import com.ahmeteminsaglik.neo4jsocialmedya.model.BookOL;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.CustomLog;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.DataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.Result;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessDataResult;
import com.ahmeteminsaglik.neo4jsocialmedya.utility.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin
public class BookController {
    @Autowired
    private BookService service;
    private static CustomLog log = new CustomLog(UserMapper.class);

    @GetMapping("/start")
    public String startImageSave() {
        log.info("DB'ye imageByte  eklenmeye basladi");
        List<Book> bookList = service.findAll();
        for (int i = 0; i < bookList.size(); i++) {
//        for (int i = 44; i <47; i++) {
            log.info("image book index process : "+bookList.size()+"/"+(i+1)+" book name: "+bookList.get(i).getName());
            try {
                URL url = null;
//                url = new URL("https://covers.openlibrary.org/b/id/3993778.jpg");
                url = new URL(bookList.get(i).getImgUrl());
                BufferedImage image = ImageIO.read(url);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(image, "jpg", baos);
                byte[] imageInByte = baos.toByteArray();
                bookList.get(i).setImageBytes(imageInByte);
            }/* catch (MalformedURLException e) {
                log.error(e.getMessage());
//                throw new RuntimeException(e);
            }*/ catch (IOException e) {
                log.error(e.getMessage());
                bookList.get(0).setImageBytes(null);
            }
        }
        service.save(bookList);
        StringBuilder sb=new StringBuilder();
        for(Book tmp : bookList){
            sb.append(tmp.getId()).append(" -) ").append(tmp.getName()).append("<br/>");
        }
       return sb.toString();
    }

    @GetMapping
    public ResponseEntity<DataResult<List<Book>>> getAll() {
        List<Book> bookList = service.findAll();
        DataResult result = new SuccessDataResult(bookList, "All Book data is retrieved");
        return ResponseEntity.status(HttpStatus.OK).body(result);

    }

    @GetMapping("/{userId}/{bookId}")
    public ResponseEntity<DataResult<Book>> getBookByUserIdReadBookId(@PathVariable long userId, @PathVariable long bookId) {
        List<Book> booklist = service.getAllReadBooksByUserId(userId);
        Book book = null;// = service.getBookByUserIdReadBookId(userId, bookId);
        System.out.println("bookId =" + bookId);

        for (Book tmp : booklist) {
            System.out.println("tmp id : " + tmp.getId());
            if (tmp.getId() == bookId) {
                book = tmp;
                System.out.println("ESLESDI ");
                break;
            }
        }
        DataResult result = new SuccessDataResult(book, "User(" + userId + ") read Book(" + bookId + ") data is retrieved.");
        System.out.println(" result : " + result.getData());
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/{name}")
    public ResponseEntity<DataResult<Book>> getBookByName(@PathVariable String name) {
        Book book = service.findByName(name);
        System.out.println(" Book >>>>>>>>>>>> book : " + book);
        DataResult result = new SuccessDataResult(book, "Book is retrieved.");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping()
    public ResponseEntity<DataResult<Book>> save(@RequestBody Book book) {
        book = service.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessDataResult<>(book, "Book is saved "));
    }

    public ResponseEntity<DataResult<List<Book>>> saveAllBook(@RequestBody List<Book> list) {
        list = service.save(list);
        DataResult result = new SuccessDataResult(list, "BookOL list is saved.");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    /*public ResponseEntity<DataResult<BookOL>> save(@RequestBody BookOL bookOL) {
        bookOL = bookOLService.save(bookOL);
        DataResult result = new SuccessDataResult(bookOL, "BookOL is saved.");
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


*/
    @GetMapping("/readby/{userId}")
    public DataResult<List<Book>> getAllReadBookByUserId(@PathVariable long userId) {
        return new SuccessDataResult<>(service.getAllReadBooksByUserId(userId), "Read book data is retrived successfuly");
    }

    @GetMapping("/recommend/point")
    public DataResult<List<Book>> getRecommenedAuthorListByHighestPoint() {
        return new SuccessDataResult<>(service.findByHighestPoint(), "Data retrived Successfully");
    }

    @GetMapping("/recommend/totalread")
    public DataResult<List<Book>> getByHighestTotalRead() {
        return new SuccessDataResult<>(service.findByHighestTotalRead(), "Data retrived Successfully");
    }

    @GetMapping("/recommend/friend/{userId}")
    public DataResult<List<Book>> getByMostReadBookFromFollowings(@PathVariable long userId) {
        return new SuccessDataResult<>(service.findByMostReadBookFromFollowings(userId), "Data retrived Successfully");
    }

    @PostMapping("/{userId}/read/{bookId}")
    public Result createNewConnectionFollowUser(@PathVariable long userId, @PathVariable long bookId) {
        service.createConnectionUserReadBook(userId, bookId);
        return new SuccessResult("Connection is created");
    }

    @GetMapping("/fix")
    public void fixBookData() {
        service.fixBookData();
    }

}
