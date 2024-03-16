class UserFriendDTO {
  late int _id;
  late String _name;
  late String _lastname;
  late String _gender;
  late String _imgurl;
  late int _totalReadBook;

  UserFriendDTO(
      {required id,
      required name,
      required lastname,
      required gender,
      required totalReadBook,
      required imgurl}) {
    _id = id;
    _name = name;
    _lastname = lastname;
    _gender = gender;
    _totalReadBook = totalReadBook;
    _imgurl = imgurl;
  }

  factory UserFriendDTO.fromJson(Map<String, dynamic> json) {
    return UserFriendDTO(
        id: json['id'],
        name: json['name'],
        lastname: json['lastname'],
        gender: json['gender'],
        totalReadBook: json['totaReadBook'],
        imgurl: json['imgurl']);
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'lastname': lastname,
      'gender': gender,
      'totalReadBook': totalReadBook,
      'imgurl': imgurl
    };
  }


  int get totalReadBook => _totalReadBook;

  set totalReadBook(int value) {
    _totalReadBook = value;
  }

  String get imgurl => _imgurl;

  set imgurl(String value) {
    _imgurl = value;
  }

  String get gender => _gender;

  set gender(String value) {
    _gender = value;
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

  set id(int value) {
    _id = value;
  }
}
