import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_book_app/repo/UserRepository.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';
import 'package:logger/logger.dart';
import '../model/User.dart';
import 'BaseHttpRequest.dart';
import 'Model/ResponseEntity.dart';
import 'package:http/http.dart' as http;

class HttpRequestUser {
  static const String _classUrl = "/users";
  static final String _baseUrl = BaseHttpRequestConfig.baseUrl + _classUrl;
  static var log = Logger(printer: PrettyPrinter(colors: false));

  static Future<ResponseEntity> login(String username, String password) async {
    String url = "$_baseUrl/login";
    log.i("URL : $url");
    Map<String, dynamic> requestData = {
      "username": username,
      "password": password,
    };
    var resp = await Dio().post(url, data: requestData);
    ResponseEntity respEntity = ResponseEntity.fromJson(resp.data);
    return respEntity;
  }

  static Future<List<User>> getRecommendUserList() async {
    List<User> userList = [];
    Uri url =
        Uri.parse("$_baseUrl/recommend/user/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserList(respEntity.data);
    }
    return userList;
  }

  /*@POST("{userId}/read/{bookId}")
    Call<RestApiResponse> createConnectionUserReadBook(@Path("userId") long userId, @Path("bookId") long bookId);*/

  static Future<void> setUserReadBookConnection(
      int userId, int bookId, rate) async {
    String url = "$_baseUrl/read-book?userId=$userId&bookId=$bookId&rate=$rate";
    log.i("SET CONNECTIONURL : $url");
    // http: //localhost:8080/users/read-book?userId=72&bookId=139&rate=1
    var resp = await Dio().post(url);
    log.i("SET CONNECTION RESPOND : " + resp.toString());
  }

  static Future<void> destroyUserReadBookConnection(
      int userId, int bookId) async {
    String url = "$_baseUrl/readbooks?userId=$userId&bookId=$bookId";
    log.i("DESTROY CONNECTION URL : $url");
    var resp = await Dio().delete(url);
    log.i("DESTROY CONNECTION  RESPOND : " + resp.toString());
  }

  static Future<int> getUserBookCount() async {
    String url = "$_baseUrl/count/book?userId=${SharedPrefUtils.getUserId()}";
    log.i("URL : $url");
    var resp = await Dio().get(url);
    ResponseEntity respEntity = ResponseEntity.fromJson(resp.data);
    return respEntity.data;
  }

  static Future<List<User>> getFollowingUserList() async {
    List<User> userList = [];
    Uri url =
    Uri.parse("$_baseUrl/following/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserList(respEntity.data);
    }
    return userList;
  }

  static Future<List<User>> getFollowerUserList() async {
    List<User> userList = [];
    Uri url =
    Uri.parse("$_baseUrl/follower/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserList(respEntity.data);
    }
    return userList;
  }
}
