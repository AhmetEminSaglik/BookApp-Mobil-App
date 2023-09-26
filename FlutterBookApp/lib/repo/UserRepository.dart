import '../model/User.dart';

class UserRepository {
  static User parseUser(Map<String,dynamic> json) {
    return User.fromJson(json);
  }
}
