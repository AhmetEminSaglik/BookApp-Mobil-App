import 'dart:convert';

import 'package:dio/dio.dart';
import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';
import 'package:flutter_book_app/repo/UserRepository.dart';
import 'package:flutter_book_app/util/SharedPrefUtils.dart';
import 'package:logger/logger.dart';
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

  static Future<List<UserFriendDTO>> getRecommendUserList() async {
    List<UserFriendDTO> userList = [];
    Uri url =
        Uri.parse("$_baseUrl/recommend/user/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserFriendDTOList(respEntity.data);
    }
    return userList;
  }

  static Future<List<UserFriendDTO>> getRandomRecommendUserList() async {
    List<UserFriendDTO> userList = [];
    Uri url = Uri.parse(
        "$_baseUrl/recommend/random/user/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserFriendDTOList(respEntity.data);
    }
    return userList;
  }

  static Future<int> getUserBookCount() async {
    String url = "$_baseUrl/count/book?userId=${SharedPrefUtils.getUserId()}";
    log.i("URL : $url");
    var resp = await Dio().get(url);
    ResponseEntity respEntity = ResponseEntity.fromJson(resp.data);
    return respEntity.data;
  }

  static Future<List<UserFriendDTO>> getFollowingUserList() async {
    List<UserFriendDTO> userList = [];
    Uri url = Uri.parse("$_baseUrl/following/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserFriendDTOList(respEntity.data);
    }
    return userList;
  }

  static Future<List<UserFriendDTO>> getFollowerUserList() async {
    List<UserFriendDTO> userList = [];
    Uri url = Uri.parse("$_baseUrl/follower/${SharedPrefUtils.getUserId()}");
    log.i("URL : $url");
    var resp = await http.get(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    if (respEntity.success) {
      userList = UserRepository.parseUserFriendDTOList(respEntity.data);
    }
    return userList;
  }

  static Future<bool> removeFollower(int followerId) async {
    Uri url = Uri.parse(
        "$_baseUrl/${SharedPrefUtils.getUserId()}/follower/$followerId");
    log.i("URL : $url");
    var resp = await http.delete(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    bool result = false;
    if (respEntity.success) {
      result = respEntity.success;
    }
    return result;
  }

  static Future<bool> removeFollowing(int followerId) async {
    Uri url = Uri.parse(
        "$_baseUrl/${SharedPrefUtils.getUserId()}/following/$followerId");
    log.i("URL : $url");
    var resp = await http.delete(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    bool result = false;
    if (respEntity.success) {
      result = respEntity.success;
    }
    return result;
  }

  static Future<bool> followUser(int userFriendId) async {
    Uri url = Uri.parse(
        "$_baseUrl/${SharedPrefUtils.getUserId()}/follow/$userFriendId");
    log.i("URL : $url");
    var resp = await http.post(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    bool result = false;
    if (respEntity.success) {
      result = respEntity.success;
    }
    return result;
  }

  static Future<bool> unfollowUser(int userFriendId) async {
    Uri url = Uri.parse(
        "$_baseUrl/${SharedPrefUtils.getUserId()}/following/$userFriendId");
    log.i("URL : $url");
    var resp = await http.delete(url);
    Map<String, dynamic> jsonData = json.decode(resp.body);
    ResponseEntity respEntity = ResponseEntity.fromJson(jsonData);
    bool result = false;
    if (respEntity.success) {
      result = respEntity.success;
    }
    return result;
  }
}
