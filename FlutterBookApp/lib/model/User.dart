class User {
  late int _id;
  late String _name;
  late String _lastname;
  late String _username;
  late String _password;
  late String _totalFollowers;
  late String _totalFollowed;

  User(
      {required id,
      required name,
      required lastname,
      required username,
      required password,
      required totalFollowers,
      required totalFollowed}) {
    _name = name;
    _lastname = lastname;
    _username = username;
    _password = password;
    _totalFollowers = totalFollowers;
    _totalFollowed = totalFollowed;
  }

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json["id"] as int,
      name: json["name"] as String,
      lastname: json["lastname"] as String,
      username: json["username"] as String,
      password: json["password"] as String,
      totalFollowers: json["totalFollowers"] as int,
      totalFollowed: json["totalFollowed"] as int,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'lastname': lastname,
      'username': username,
      '_password': _password,
      '_totalFollowers': _totalFollowers,
      'totalFollowed': totalFollowed,
    };
  }

  String get totalFollowed => _totalFollowed;

  set totalFollowed(String value) {
    _totalFollowed = value;
  }

  String get totalFollowers => _totalFollowers;

  set totalFollowers(String value) {
    _totalFollowers = value;
  }

  String get password => _password;

  set password(String value) {
    _password = value;
  }

  String get username => _username;

  set username(String value) {
    _username = value;
  }

  String get lastname => _lastname;

  set lastname(String value) {
    _lastname = value;
  }

  String get name => _name;

  set name(String value) {
    _name = value;
  }

  int get id => _id;
}
