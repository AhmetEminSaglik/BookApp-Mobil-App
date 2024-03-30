class User {
  late int _id;
  late String _name;
  late String _lastname;
  late String _username;
  late String _password;
  late String _gender;
  late int _followers;
  late int _following;
  late String _imgUrl;

  User(
      {required id,
      required name,
      required lastname,
      required username,
      required password,
      required gender,
      required followers,
      required following,
      required imgUrl}) {
    _id = id;
    _name = name;
    _lastname = lastname;
    _username = username;
    _password = password;
    _followers = followers;
    _following = following;
    _imgUrl = imgUrl;
    _gender = gender;
  }

  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json["id"] as int,
      name: json["name"] as String,
      lastname: json["lastname"] as String,
      username: json["username"] as String,
      password: json["password"] as String,
      gender: json["gender"] as String,
      followers: json["followers"] as int,
      following: json["following"] as int,
      imgUrl: json["imgUrl"] as String,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'lastname': lastname,
      'username': username,
      '_password': password,
      '_followers': followers,
      'following': following,
      'imgUrl': imgUrl,
    };
  }

  int get following => _following;

  set following(int value) {
    _following = value;
  }

  int get followers => _followers;

  set followers(int value) {
    _followers = value;
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

  String get imgUrl => _imgUrl;

  set imgUrl(String value) {
    _imgUrl = value;
  }

  @override
  String toString() {
    return 'User{_id: $_id, _name: $_name, _lastname: $_lastname, _username: $_username, _password: $_password, _gender: $_gender, _followers: $_followers, _following: $_following, _imgUrl: $_imgUrl}';
  }
}
