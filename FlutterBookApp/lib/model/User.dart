class User {
  late int _id;
  late String _name;
  late String _lastname;
  late String _username;
  late String _password;
  late int _totalFollowers;
  late int _totalFollowed;

  User(
      {required id,
      required name,
      required lastname,
      required username,
      required password,
      required totalFollowers,
      required totalFollowed}) {
    _id = id;
    _name = name;
    _lastname = lastname;
    _username = username;
    _password = password;
    _totalFollowers = totalFollowers;
    _totalFollowed = totalFollowed;
  }

  factory User.fromJson(Map<String, dynamic> json) {
    print("gelen json : $json");
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
      '_password': password,
      '_totalFollowers': totalFollowers,
      'totalFollowed': totalFollowed,
    };
  }

  int get totalFollowed => _totalFollowed;

  set totalFollowed(int value) {
    _totalFollowed = value;
  }

  int get totalFollowers => _totalFollowers;

  set totalFollowers(int value) {
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
