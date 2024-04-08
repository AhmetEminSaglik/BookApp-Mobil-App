class UserFriendDTO {
  late int _id;
  late String _name;
  late String _lastname;
  late String _gender;
  late String _imgUrl;
  late int _totalFollowers;
  late int _totalFollowing;
  late int _totalReadBook;

  UserFriendDTO(
      {required id,
      required name,
      required lastname,
      required gender,
      required totalReadBook,
      required totalFollowers,
      required totalFollowing,
      required imgUrl}) {
    _id = id;
    _name = name;
    _lastname = lastname;
    _gender = gender;
    _totalReadBook = totalReadBook;
    _imgUrl = imgUrl;
    _totalFollowers = totalFollowers;
    _totalFollowing = totalFollowing;
  }

  factory UserFriendDTO.empty() {
    return UserFriendDTO(
        id: -1,
        name: "",
        lastname: "",
        gender: "",
        totalReadBook: -1,
        totalFollowers: -1,
        totalFollowing: -1,
        imgUrl: "");
  }

  factory UserFriendDTO.fromJson(Map<String, dynamic> json) {
    return UserFriendDTO(
        id: json['id'],
        name: json['name'],
        lastname: json['lastname'],
        gender: json['gender'],
        totalReadBook: json['totaReadBook'],
        totalFollowers: json['totalFollowers'],
        totalFollowing: json['totalFollowing'],
        imgUrl: json['imgUrl']);
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'lastname': lastname,
      'gender': gender,
      'totalReadBook': totalReadBook,
      'totalFollowing': totalFollowing,
      'totalFollowers': totalFollowers,
      'imgUrl': imgUrl
    };
  }

  @override
  String toString() {
    return "UserFriendDTO{id:$id,name:$name,lastname:$lastname,gender:$gender,totalReadBook:$totalReadBook,totalFollowers:$totalFollowers,totalFollowing:$totalFollowing,imgUrl:$imgUrl}";
    // return "$id-$name $lastname}";
  }

  int get totalReadBook => _totalReadBook;

  set totalReadBook(int value) {
    _totalReadBook = value;
  }

  String get imgUrl => _imgUrl;

  set imgUrl(String value) {
    _imgUrl = value;
  }

  String get gender => _gender;

  set gender(String value) {
    _gender = value;
  }

  int get totalFollowers => _totalFollowers;

  set totalFollowers(int value) {
    _totalFollowers = value;
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

  int get totalFollowing => _totalFollowing;

  set totalFollowing(int value) {
    _totalFollowing = value;
  }
}
