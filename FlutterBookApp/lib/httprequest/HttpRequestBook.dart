import 'dart:convert';

import 'package:flutter_book_app/repo/BookRepository.dart';
import 'package:logger/logger.dart';

import '../model/Book.dart';
import '../util/SharedPrefUtils.dart';
import 'BaseHttpRequest.dart';
import 'package:http/http.dart' as http;

import 'Model/ResponseEntity.dart';

class HttpRequestBook {
  static const String _classUrl = "/books";
  static final String _baseUrl = BaseHttpRequestConfig.baseUrl + _classUrl;
  static var log = Logger(printer: PrettyPrinter(colors: false));

  static Future<List<Book>> getRecommendedBookListByPoint() async {
    List<Book> bookList = [];
    Uri url = Uri.parse("$_baseUrl/recommend/point");
    // Uri url = Uri.parse("$_baseUrl");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      bookList = BookRepository.parseBookList(respEntity.data);
    }
    return bookList;
  }

  static Future<List<Book>> getReadBookList() async {
    List<Book> bookList = [];
    Uri url = Uri.parse("$_baseUrl/readby/${SharedPrefUtils.getUserId()}");
    // Uri url = Uri.parse("$_baseUrl");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      bookList = BookRepository.parseBookList(respEntity.data);
    }
    return bookList;
  }

  static Future<List<Book>> getRecommendedBookListByTotalRead() async {
    List<Book> bookList = [];
    Uri url = Uri.parse("$_baseUrl/recommend/totalread");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      bookList = BookRepository.parseBookList(respEntity.data);
    }
    return bookList;
  }

  static Future<List<Book>> getRecommendedBookListByFriendRead() async {
    List<Book> bookList = [];
    Uri url =
        Uri.parse("$_baseUrl/recommend/friend/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      bookList = BookRepository.parseBookList(respEntity.data);
    }
    return bookList;
  }

  static Future<Book?> getIfUserReadBook(int bookId) async {
    Book? book;
    Uri url = Uri.parse("$_baseUrl/${SharedPrefUtils.getUserId()}/$bookId");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    // if (respEntity.success) {
    if (respEntity.data != null) {
      book = BookRepository.parseBook(respEntity.data);
    }
    // }
    return book;
  }
}
