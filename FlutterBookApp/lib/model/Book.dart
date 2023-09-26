class Book {
  late int _id;
  late String _desc;
  late String _imgUrl;
  late String _isbn;
  late String _name;
  late double _point;
  late int _totalRead;
  late String _webUrl;

  Book({required id,
    required desc,
    required imgUrl,
    required isbn,
    required name,
    required point,
    required totalRead,
    required webUrl});

  factory Book.fromJson(Map<String, dynamic> json) {
    return Book(
        id: json["id"] as String,
        desc: json["desc"] as String,
        imgUrl: json["imgUrl"] as String,
        isbn: json["isbn"] as String,
        name: json["name"] as String,
        point: json["point"] as double,
        totalRead: json["totalRead"] as int,
        webUrl: json["webUrl"] as String);
  }

  Map<String, dynamic> toJson() {
    return {
      "id": id,
      "desc": desc,
      "imgUrl": imgUrl,
      "isbn": isbn,
      "name": name,
      "point": point,
      "totalRead": totalRead,
      "webUrl": webUrl
    }
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

}
