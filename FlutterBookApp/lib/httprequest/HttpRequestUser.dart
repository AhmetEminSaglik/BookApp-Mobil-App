import 'dart:convert';
import 'package:dio/dio.dart';
import 'package:http/http.dart' as http;
import 'package:logger/logger.dart';

import '../util/HttpUtil.dart';
import 'BaseHttpRequest.dart';
import 'Model/ResponseEntity.dart';

class HttpRequestUser {
  static const String _classUrl = "/users";
  static final String _baseUrl = BaseHttpRequestConfig.baseUrl + _classUrl;
  static var log = Logger(printer: PrettyPrinter(colors: false));

  static Future<ResponseEntity> login(String username, String password) async {
    // http://localhost:8080/users/login
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
/*
Future<ResponseEntity?> login(
    {required String username, required String password}) async {
  var request = HttpRequestUser();
  ResponseEntity? respEntity;
  await request.login(username, password).then((resp) async {
    Map<String, dynamic> jsonData = json.decode(resp.body);
    respEntity = ResponseEntity.fromJson(jsonData);
    return respEntity;
  });
  return respEntity;
}*/
}
