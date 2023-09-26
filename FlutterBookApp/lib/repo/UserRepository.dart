import '../model/User.dart';

class UserRepository {
  static User parseUser(Map<String,dynamic> json) {
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
        totalFollowers: tmp['totalFollowers'],
        totalFollowed: tmp['totalFollowed'],
      );
      userList.add(user);
    }

    return userList;
  }
}
