package org.ahmeteminsaglik.bookapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@CrossOrigin
public class HomeController {

    @GetMapping()
    @ResponseBody
    public String home() {
        StringBuilder html = new StringBuilder("""
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Bloodcheck API</title>
                        <style>
                            body { font-family: Arial, sans-serif; text-align: center; padding: 20px; }
                            h1 { color: #d32f2f; }
                            ul {
                            list-style-type: none;
                            }
                            ul.show-points {
                                list-style-type: disc;
                            }
                                
                            li { margin: 10px 0; }
                            a { text-decoration: none; color: #1565c0; font-weight: bold; }
                            .role { font-size: 0.9em; color: gray; }
                            .notes-container {
                                text-align: center;
                                margin-bottom: 20px;
                            }
                            .notes {
                                text-align: left;
                                margin-left: auto;
                                margin-right: auto;
                                width: 80%;
                            }
                        </style>
                    </head>
                    <body>
                        <h1>Welcome to the BookApp API</h1>
                        <p>This project's is created with Spring Security. And it is using with API Key.
                         <p>Not allowed to visit API other way.</p>
                         <hr>
                        <p>But you can visit my other BloodCheck App:</p> <a href="https://bloodcheck.ahmeteminsaglik.com/api/1.0/">https://bloodcheck.ahmeteminsaglik.com/api/1.0/</a>:</p>
                        <hr>
                        <p style="color:green;font-size:20px">BookApp Google Play Link:</p>
                        <p><a href="https://play.google.com/store/apps/details?id=com.ahmeteminsaglik.flutter_book_app">https://play.google.com/store/apps/details?id=com.ahmeteminsaglik.flutter_book_app</a></p>
                        <hr> 
                        <ul>
                """);
        return html.toString();
    }

}
