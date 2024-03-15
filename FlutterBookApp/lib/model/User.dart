class User {
  late int _id;
  late String _name;
  late String _lastname;
  late String _username;
  late String _password;
  late String _gender;
  late int _totalFollowers;
  late int _totalFollowed;
  late String _imgurl;

  User(
      {required id,
      required name,
      required lastname,
      required username,
      required password,
      required gender,
      required totalFollowers,
      required totalFollowed,
      required imgurl}) {
    _id = id;
    _name = name;
    _lastname = lastname;
    _username = username;
    _password = password;
    _totalFollowers = totalFollowers;
    _totalFollowed = totalFollowed;
    _imgurl = imgurl;
    _gender = gender;
  }

  factory User.fromJson(Map<String, dynamic> json) {
    print("USER ICIN gelen json : $json");
    return User(
      id: json["id"] as int,
      name: json["name"] as String,
      lastname: json["lastname"] as String,
      username: json["username"] as String,
      password: json["password"] as String,
      gender: json["gender"] as String,
      totalFollowers: json["totalFollowers"] as int,
      totalFollowed: json["totalFollowed"] as int,
      imgurl: json["imgUrl"] as String,
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
      'imgUrl': imgurl,
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

  String get gender => _gender;

  set gender(String value) {
    _gender = value;
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

  String get imgurl => _imgurl;

  set imgurl(String value) {
    _imgurl = value;
  }

  @override
  String toString() {
    return 'User{_id: $_id, _name: $_name, _lastname: $_lastname, _username: $_username, _password: $_password, _gender: $_gender, _totalFollowers: $_totalFollowers, _totalFollowed: $_totalFollowed, _imgUrl: $_imgurl}';
  }
}
