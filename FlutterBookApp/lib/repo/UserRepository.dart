import 'package:flutter_book_app/model/dto/UserFriendDTO.dart';

import '../model/User.dart';

class UserRepository {
  static User parseUser(Map<String, dynamic> json) {
    return User.fromJson(json);
  }

  static List<User> parseUserList(List<dynamic> json) {
    List<User> userList = [];
    for (var tmp in json) {
      User user = User(
        id: tmp['id'],
        name: tmp['name'],
        lastname: tmp['lastname'],
        username: tmp['username'],
        password: tmp['password'],
        gender: tmp['gender'],
        followers: tmp['followers'],
        following: tmp['following'],
        imgurl: tmp['imgUrl'],
      );
      userList.add(user);
    }

    return userList;
  }
  static List<UserFriendDTO> parseUserFriendDTOList(List<dynamic> json) {
    List<UserFriendDTO> userList = [];
    for (var tmp in json) {
      UserFriendDTO user = UserFriendDTO(
        id: tmp['id'],
        name: tmp['name'],
        lastname: tmp['lastname'],
        gender: tmp['gender'],
        totalReadBook: tmp['totalReadBook'],
        imgurl: tmp['imgUrl'],
      );
      userList.add(user);
    }

    return userList;
  }
}
