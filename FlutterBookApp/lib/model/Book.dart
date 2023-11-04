import 'package:flutter_book_app/model/Author.dart';

class Book {
  late int _id;
  late String _desc;
  late String _imgUrl;
  late String _isbn;
  late String _name;
  late double _point;
  late int _totalRead;
  late String _webUrl;
  late Author _author;

  Book(
      {required id,
      required desc,
      required imgUrl,
      required isbn,
      required name,
      required point,
      required totalRead,
      required webUrl,
      required author}) {
    _id = id;
    _desc = desc;
    _imgUrl = imgUrl;
    _isbn = isbn;
    _name = name;
    _point = point;
    _totalRead = totalRead;
    _webUrl = webUrl;
    _author = author;
  }

  factory Book.fromJson(Map<String, dynamic> json) {
    return Book(
        id: json["id"] as String,
        desc: json["description"] as String,
        imgUrl: json["imgUrl"] as String,
        isbn: json["isbn"] as String,
        name: json["name"] as String,
        point: json["point"] as double,
        totalRead: json["totalRead"] as int,
        webUrl: json["webUrl"] as String,
        author: Author.fromJson(json));
  }

  Map<String, dynamic> toJson() {
    return {
      "id": id,
      "description": desc,
      "imgUrl": imgUrl,
      "isbn": isbn,
      "name": name,
      "point": point,
      "totalRead": totalRead,
      "webUrl": webUrl,
      "author": _author.toJson()

    };
  }

  String get webUrl => _webUrl;

  set webUrl(String value) {
    _webUrl = value;
  }

  int get totalRead => _totalRead;

  set totalRead(int value) {
    _totalRead = value;
  }

  double get point => _point;

  set point(double value) {
    _point = value;
  }

  String get name => _name;

  set name(String value) {
    _name = value;
  }

  String get isbn => _isbn;

  set isbn(String value) {
    _isbn = value;
  }

  String get imgUrl => _imgUrl;

  set imgUrl(String value) {
    _imgUrl = value;
  }

  String get desc => _desc;

  set desc(String value) {
    _desc = value;
  }

  int get id => _id;

  Author get author => _author;

  set author(Author value) {
    _author = value;
  }

  @override
  String toString() {
    return 'Book{_id: $_id, _desc: $_desc, _imgUrl: $_imgUrl, _isbn: $_isbn, _name: $_name, _point: $_point, _totalRead: $_totalRead, _webUrl: $_webUrl}';
  }
}
