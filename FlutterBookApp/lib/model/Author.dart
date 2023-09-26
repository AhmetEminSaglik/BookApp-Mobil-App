class Author {
  late int _id;
  late String _key;
  late String _name;
  late String _lastname;
  late double _point;
  late int _totalBook;

  Author(
      {required id,
      required key,
      required name,
      required lastname,
      required point,
      required totalBook});

  factory Author.fromJson(Map<String, dynamic> json) {
    return Author(
        id: json["id"] as String,
        key: json["key"] as String,
        name: json["name"] as String,
        lastname: json["lastname"] as String,
        point: json["point"] as double,
        totalBook: json["totalBook"] as int);
  }

  Map<String, dynamic> toJson() {
    return {
      "id": id,
      "key": key,
      "name": name,
      "lastname": lastname,
      "point": point,
      "totalBook": totalBook
    };
  }

  int get totalBook => _totalBook;

  set totalBook(int value) {
    _totalBook = value;
  }

  double get point => _point;

  set point(double value) {
    _point = value;
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
}
