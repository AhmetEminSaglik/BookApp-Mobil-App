import 'package:flutter_book_app/model/Book.dart';
import 'package:logger/logger.dart';

class Author {
  late int _id;
  late String _key;
  late String _name;
  late String _lastname;

  late List<dynamic> _totalBook;

  Author(
      {required id,
      required key,
      required name,
      required lastname,
      required totalBook}) {
    _id = id;
    _key = key;
    _name = name;
    _lastname = lastname;
    _totalBook = totalBook;
  }

  static var log = Logger(printer: PrettyPrinter(colors: false));

  factory Author.fromJson(Map<String, dynamic> json) {
    log.i("Author.dart : json : $json");
    List<dynamic> list = json["totalBook"] != null
        ? json["totalBook"].map((book) => Book.fromJson(book)).toList()
        : [];
    return Author(
        id: json["id"] as int,
        key: json["key"] as String,
        name: json["name"] as String,
        lastname: json["lastname"] as String,
        totalBook: list);
  }

  Map<String, dynamic> toJson() {
    return {
      "id": id,
      "key": key,
      "name": name,
      "lastname": lastname,
      "totalBook": totalBook
    };
  }

  List<dynamic> get totalBook => _totalBook;

  set totalBook(List<dynamic> value) {
    _totalBook = value;
  }

  String get lastname => _lastname;

  set lastname(String value) {
    _lastname = value;
  }

  String get name => _name;

  set name(String value) {
    _name = value;
  }

  String get key => _key;

  set key(String value) {
    _key = value;
  }

  int get id => _id;

  @override
  String toString() {
    return 'Author{_id: $_id, _key: $_key, _name: $_name, _lastname: $_lastname, '
        '_totalBook: $_totalBook}';
  }
}
